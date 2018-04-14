<template>
  <div class="home-page">
    <div class="accounts">
      <h2>Accounts</h2>
      <ol>
        <li v-for="account in accounts" :key="account.privateKey">
          <span @click="selectAccount(account)">
            {{ account.address }}
          </span>
        </li>
      </ol>
      <button @click="newAccount">New Account</button>
    </div>
    <div v-if="account">
      <dl>
        <dt>Address</dt>
        <dd>{{ account.address }}</dd>
        <dt>Public Key</dt>
        <dd>{{ account.publicKey }}</dd>
        <dt>Private Key</dt>
        <dd>{{ account.privateKey }}</dd>
      </dl>
      <div>
        <h2>Send Transaction</h2>
        <div>
          From:
          <input type="text" v-model="transaction.from">
        </div>
        <div>
          To:
          <input type="text" v-model="transaction.to">
        </div>
        <div>
          Amount:
          <input type="text" v-model="transaction.amount">
        </div>
        <button @click="sendTx">Send</button>
      </div>
      <div>
        {{ rawTransaction }}
      </div>
    </div>
    <div>
      <h2>Transactions ({{ transactions.length }})</h2>
      <ol>
        <li v-for="transaction in transactions" :key="transaction.hash">
          {{ transaction }}
        </li>
      </ol>
    </div>
  </div>
</template>

<script>
import { BigNumber } from 'bignumber.js';
import moment from 'moment';
import axios from 'axios';
import nacl from 'tweetnacl';
import Hashes from 'jshashes';

const RMD160 = new Hashes.RMD160;

function toHex(data) {
  return Array.from(data)
              .map(i => ('0' + i.toString(16))
              .slice(-2)).join('').toUpperCase();
}

function fromHex(hex, bytes) {
  const data = bytes ? ('0'.repeat(bytes * 2) + hex).slice(-bytes * 2) : hex;
  var r = Array(data.length / 2).fill().map((_, i) => i * 2);
  var a = r.map(i => parseInt(data.slice(i, i + 2), 16));
  return Uint8Array.from(a);
}

function concatenate(resultConstructor, ...arrays) {
  const length = arrays.map(i => i.length).reduce((a, b) => a + b);
  const result = new resultConstructor(length);
  [0, ...arrays].reduce((offset, arr) => {
    result.set(arr, offset);
    return offset + arr.length;
  });
  return result;
}

async function callMethod(method, params) {
  const id = moment().valueOf();
  const url = 'http://localhost:8123';
  const data = { jsonrpc: '2.0', id, method, params };
  const config = {
    headers: { 'content-type': 'application/json' },
  };
  const response = await axios.post(url, data, config);
  return response.data;
}

export default {
  name: 'HomePage',
  data() {
    return {
      accounts: [],
      account: null,
      transaction: {
        hash: '',
        timestamp: '',
        from: '',
        to: '',
        amount: '100',
        signature: '',
        publicKey: '',
        confirmed: false,
      },
      rawTransaction: '',
      transactions: [],
    };
  },
  created: function() {
    this.loadAccounts();
  },
  methods: {
    saveAccounts() {
      localStorage.setItem('accounts', JSON.stringify(this.accounts));
    },
    loadAccounts() {
      this.accounts = JSON.parse(localStorage.getItem('accounts') || '[]');
    },
    newAccount() {
      const key = nacl.sign.keyPair();
      this.accounts.push({
        privateKey: toHex(key.secretKey),
        publicKey: toHex(key.publicKey),
        address: RMD160.hex(toHex(key.publicKey)).toUpperCase(),
      });
      this.saveAccounts();
    },
    selectAccount(account) {
      this.account = account;
      this.transaction.from = account.address;
    },
    sendTx() {
      this.transaction.timestamp = moment().unix();

      const data = this.rawTxData();
      this.rawTransaction = toHex(data);

      const privateKey = fromHex(this.account.privateKey);
      const signature = nacl.sign.detached(data, privateKey);

      this.transaction.signature = toHex(signature);
      this.transaction.publicKey = this.account.publicKey;

      this.callSendTransaction();
    },
    rawTxData() {
      const { timestamp, from, to } = this.transaction;
      const amount = new BigNumber(this.transaction.amount);
      return concatenate(
        Uint8Array,
        fromHex(timestamp.toString(16), 4).reverse(),
        fromHex(from, 20),
        fromHex(to, 20),
        fromHex(amount.toString(16), 32).reverse(),
      );
    },
    async callSendTransaction() {
      const {
        timestamp, from, to, amount, signature, publicKey,
      } = this.transaction;

      this.transaction.hash = '';

      try {
        const data = await callMethod('send_transaction', {
          timestamp, from, to, amount, signature, publicKey,
        });
        if (data.error) {
          console.error(data.error);
          return;
        }
        console.log(data.result);

        this.transaction.hash = data.result.hash;
        this.transactions.push(Object.assign({}, this.transaction));

        this.resetTransaction();
      } catch (e) {
        console.error(e);
      }
    },
    resetTransaction() {
      this.transaction.hash = '';
      this.transaction.timestamp = '';
      this.transaction.signature = '';
      this.confirmed = false;
    },
  },
}
</script>

<style scoped>
.home-page {
  word-break: break-all;
}

.accounts span {
  cursor: pointer;
}

button {
  cursor: pointer;
  padding: 0 10px;
  height: 30px;
  border: 1px solid #CCC;
  border-radius: 4px;
}

dt {
  font-weight: bold;
}

dd {
  margin-bottom: 10px;
  margin-left: 10px;
}

input {
  padding: 0 10px;
  width: 80%;
  height: 30px;
  border: 1px solid #CCC;
  border-radius: 4px;
}
</style>
