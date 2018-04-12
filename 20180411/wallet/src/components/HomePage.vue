<template>
  <div class="home-page">
    <div class="accounts">
      <h2>Accounts</h2>
      <ol>
        <li v-for="account in accounts" :key="account.privateKey"
          class="account"
          @click="selectAccount(account)"
        >
          {{ account.address }}
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
      <div v-if="transaction">
        {{ JSON.stringify(transaction) }}
      </div>
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
        rawData: '',
        signature: '',
        publicKey: '',
      },
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
      const amount = new BigNumber(this.transaction.amount);
      const data = concatenate(
        Uint8Array,
        fromHex(this.transaction.timestamp.toString(16), 4).reverse(),
        fromHex(this.transaction.from, 20),
        fromHex(this.transaction.to, 20),
        fromHex(amount.toString(16), 32).reverse(),
      );
      this.transaction.rawData = toHex(data);
      const privateKey = fromHex(this.account.privateKey);
      this.transaction.signature = toHex(nacl.sign.detached(data, privateKey));
      this.transaction.publicKey = this.account.publicKey;
      this.callSendTransaction();
    },
    async callSendTransaction() {
      const {
        timestamp, from, to, amount, signature, publicKey,
      } = this.transaction;
      try {
        const data = await this.callMethod('send_transaction', {
          timestamp, from, to, amount, signature, publicKey,
        });
        if (data.error) {
          console.error(data.error);
          this.transaction.hash = '';
          return;
        }
        console.log(data.result);
        this.transaction.hash = data.result.hash;
      } catch (e) {
        console.error(e);
      }
    },
    async callMethod(method, params) {
      const id = moment().valueOf();
      const url = 'http://localhost:8123';
      const data = { jsonrpc: '2.0', id, method, params };
      const config = {
        headers: { 'content-type': 'application/json' },
      };
      const response = await axios.post(url, data, config);
      return response.data;
    },
  }
}
</script>

<style scoped>
.home-page {
  word-break: break-all;
}

.accounts li {
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
