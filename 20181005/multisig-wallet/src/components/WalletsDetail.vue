<template>
    <div class="wallet">
        <h1>Wallet - {{ name }}</h1>
        <p>
            <a
                    class="btn btn-info" target="_blank"
                    :href="`https://ropsten.etherscan.io/address/${address}`">
                {{ address }}
            </a>
        </p>
        <p>
            Balance: {{ balance }}
            &nbsp;
            &nbsp;
            <button class="btn btn-primary" @click="deposit">
                Deposit
            </button>
        </p>
        <hr/>
        <div>
            <p>Transfer</p>
            <div class="form-group">
                <input v-model="transaction.address"
                       type="text" class="form-control" placeholder="Address"/>
            </div>
            <div class="form-group">
                <input v-model="transaction.amount"
                       type="text" class="form-control" placeholder="Ether"/>
            </div>
            <div class="form-group">
                <input v-model="transaction.data"
                       type="text" class="form-control" placeholder="Data"/>
            </div>
            <button class="btn btn-primary" @click="send">
                Send
            </button>
        </div>
        <hr/>
        <div class="owners">
            <h2>Owners</h2>
            <ol>
                <li v-for="owner in owners">
                    <a
                            class="btn btn-info btn-sm" target="_blank"
                            :href="`https://ropsten.etherscan.io/address/${owner.address}`">
                        {{ owner.address }}
                    </a>
                </li>
            </ol>
        </div>
        <p>Required Count: {{ requiredCount }}</p>
        <h2>PendingTransactions</h2>
        <ol class="list-group">
            <li class="list-group-item" v-for="transaction in transactions">
                <p>
                    <span class="badge badge-dark">
                        {{ transaction.id }}
                    </span>
                </p>
                <p>
                    To:
                    <a
                            class="btn btn-info btn-sm" target="_blank"
                            :href="`https://ropsten.etherscan.io/address/${transaction.to}`">
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
                    <button v-if="!transaction.confirmed"
                            class="btn btn-success"
                            @click="confirm(transaction.id)">
                        Confirm
                    </button>
                    <button v-if="transaction.confirmed"
                            class="btn btn-danger"
                            @click="revoke(transaction.id)">
                        Revoke
                    </button>
                </div>
            </li>
        </ol>
    </div>
</template>

<script>
  import { mapState } from 'vuex';

  import { MultiSigWallet } from '../utils/contracts';

  export default {
    name: 'WalletDetail',
    props: ['address'],
    data() {
      return {
        wallet: null,
        name: '',
        balance: '',
        owners: [],
        requiredCount: '',
        transactions: [],
        transaction: {
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
      this.wallet = MultiSigWallet.at(this.address);
      await this.reload();
    },
    methods: {
      async reload() {
        this.loadOwners();
        this.loadPendingTransactions();

        try {
          this.name = await this.wallet.name();
          this.requiredCount = (await this.wallet.required()).valueOf();

          const balance = await web3.eth.getBalance(this.address);
          this.balance = web3.utils.fromWei(balance);
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
            }))
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
            transactions,
            confirmations,
          } = this.wallet;

          this.transactions = await Promise.all(
            [...Array(count).keys()].map(async (i) => {
              const txId = await getPendingTransactionId(i);
              const tx = await transactions(txId);
              const confirmCount = await getConfirmationsCount(txId);
              const confirmed = await confirmations(txId, this.account);
              return {
                id: txId.toString(),
                sender: tx[0],
                to: tx[1],
                amount: web3.utils.fromWei(tx[2].toString()),
                data: tx[3],
                confirmedCount: confirmCount.toString(),
                confirmed,
              };
            })
          );
        } catch (e) {
          console.error(e);
        }
      },
      async deposit() {
        const { sendTransaction } = web3.eth;
        const amount = prompt('How many ethers?');
        if (!amount) {
          return;
        }
        const value = web3.utils.toWei(amount);
        try {
          await sendTransaction({
            from: this.account,
            to: this.address,
            value: value,
          });
          alert('Deposit!');
          this.reload();
        } catch (e) {
          console.error(e);
        }
      },
      async send() {
        const { address, amount, data } = this.transaction;
        const value = web3.utils.toWei(amount);
        try {
          await this.wallet.requestTransaction(address, value, data, {
            from: this.account,
          });
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
  }
</script>

<style scoped>
    .owners li {
        margin-top: 2px;
        margin-bottom: 2px;
    }
</style>
