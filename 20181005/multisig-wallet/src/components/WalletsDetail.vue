<template>
  <div class="wallet">
    <h1>Wallet - {{ name }}</h1>
    <p>
      <a
        class="btn btn-info"
        target="_blank"
        :href="`https://ropsten.etherscan.io/address/${address}`"
      >
        {{ address }}
      </a>
    </p>
    <p>
      Balance: {{ balance }}
            &nbsp;
            &nbsp;
      <button
        class="btn btn-primary"
        @click="deposit"
      >
        Deposit
      </button>
    </p>
    <hr>
    <div>
      <p>Transfer</p>
      <div class="form-group">
        <input
          v-model="transactionForm.address"
          type="text"
          class="form-control"
          placeholder="Address"
        >
      </div>
      <div class="form-group">
        <input
          v-model="transactionForm.amount"
          type="text"
          class="form-control"
          placeholder="Ether"
        >
      </div>
      <div class="form-group">
        <input
          v-model="transactionForm.data"
          type="text"
          class="form-control"
          placeholder="Data"
        >
      </div>
      <button
        class="btn btn-primary"
        @click="send"
      >
        Send
      </button>
    </div>
    <hr>
    <div class="owners">
      <h2>Owners</h2>
      <ol>
        <li
          v-for="owner in owners"
          :key="owner.address"
        >
          <a
            class="btn btn-info btn-sm"
            target="_blank"
            :href="`https://ropsten.etherscan.io/address/${owner.address}`"
          >
            {{ owner.address }}
          </a>
        </li>
      </ol>
    </div>
    <p>Required Count: {{ requiredCount }}</p>
    <h2>PendingTransactions</h2>
    <ol class="list-group">
      <li
        v-for="transaction in transactions"
        :key="transaction.id"
        class="list-group-item"
      >
        <p>
          <span class="badge badge-dark">
            {{ transaction.id }}
          </span>
        </p>
        <p>
          To:
          <a
            class="btn btn-info btn-sm"
            target="_blank"
            :href="`https://ropsten.etherscan.io/address/${transaction.to}`"
          >
            {{ transaction.to }}
          </a>
        </p>
        <p>
          Value: {{ transaction.amount }} ether
        </p>
        <p v-if="transaction.data != '0x'">
          Data: {{ transaction.data }}
        </p>
        <div>
          Confirmed: {{ transaction.confirmedCount }}
                    &nbsp;&nbsp;
          <button
            v-if="!transaction.confirmed"
            class="btn btn-success"
            @click="confirm(transaction.id)"
          >
            Confirm
          </button>
          <button
            v-if="transaction.confirmed"
            class="btn btn-danger"
            @click="revoke(transaction.id)"
          >
            Revoke
          </button>
        </div>
      </li>
    </ol>
  </div>
</template>

<script>
import { mapState } from 'vuex';

import { toHex, toWei, fromWei, toAscii, fromAscii } from 'web3-utils';

import { MultiSigWallet } from '../utils/contracts';

export default {
  name: 'WalletDetail',
  props: {
    address: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      wallet: null,
      name: '',
      balance: '',
      owners: [],
      requiredCount: '',
      transactions: [],
      transactionForm: {
        address: '',
        amount: '',
        data: '',
      },
    };
  },
  computed: mapState([
    'account',
    'walletFactory',
  ]),
  async created() {
    this.wallet = await MultiSigWallet.at(this.address);
    await this.reload();
  },
  methods: {
    async reload() {
      this.loadOwners();
      this.loadPendingTransactions();

      try {
        this.name = await this.wallet.getName();
        this.requiredCount = (await this.wallet.getRequired()).valueOf();

        const balance = await ethereum.request({
          method: 'eth_getBalance',
          params: [this.address, 'latest'],
        });
        this.balance = fromWei(balance);
      } catch (e) {
        console.error(e);
      }
    },
    async loadOwners() {
      try {
        const count = (await this.wallet.getOwnersCount()).toNumber();

        this.owners = await Promise.all(
          [...Array(count).keys()].map(async (i) => ({
            address: await this.wallet.getOwner(i),
          })),
        );
      } catch (e) {
        console.error(e);
      }
    },
    async loadPendingTransactions() {
      try {
        const count = (await this.wallet.getPendingsCount()).toNumber();

        const {
          getPendingTransactionId,
          getConfirmationsCount,
          getTransaction,
          getConfirmed,
        } = this.wallet;

        this.transactions = await Promise.all(
          [...Array(count).keys()].map(async (i) => {
            const txId = await getPendingTransactionId(i);
            const tx = await getTransaction(txId);
            const confirmCount = await getConfirmationsCount(txId);
            const confirmed = await getConfirmed(txId, this.account);
            return {
              id: txId.toString(),
              sender: tx[0],
              to: tx[1],
              amount: fromWei(tx[2].toString()),
              data: unescape(toAscii(tx[3])),
              confirmedCount: confirmCount.toString(),
              confirmed,
            };
          }),
        );
      } catch (e) {
        console.error(e);
      }
    },
    async deposit() {
      const amount = prompt('How many ethers?');
      if (!amount) {
        return;
      }
      const value = toHex(toWei(amount));
      try {
        // TODO: ethereum.request은 블럭 컨펌과 무관 → wating 필요함.
        await ethereum.request({
          method: 'eth_sendTransaction',
          params: [
            {
              from: this.account,
              to: this.address,
              value,
            },
          ],
        });
        alert('Deposit!');
        this.reload();
      } catch (e) {
        console.error(e);
      }
    },
    async send() {
      const { address, amount, data } = this.transactionForm;
      const value = toHex(toWei(amount));
      try {
        await this.wallet.requestTransaction(
          address,
          value,
          fromAscii(escape(data)),
          { from: this.account },
        );
        alert('Request transaction!');
        this.reload();
      } catch (e) {
        console.error(e);
      }
    },
    async confirm(transactionId) {
      try {
        await this.wallet.confirmTransaction(transactionId, {
          from: this.account,
        });
        alert('Confirm transaction!');
        this.reload();
      } catch (e) {
        console.error(e);
      }
    },
    async revoke(transactionId) {
      try {
        await this.wallet.revokeTransaction(transactionId, {
          from: this.account,
        });
        alert('Revoke transaction!');
        this.reload();
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style scoped>
    .owners li {
        margin-top: 2px;
        margin-bottom: 2px;
    }
</style>
