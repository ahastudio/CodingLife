import {
  sendTransaction,
} from './util';

const account = {
  privateKey: process.env.PRIVATE_KEY,
  publicKey: process.env.PUBLIC_KEY,
  address: process.env.ADDRESS,
};

const template = `
  <div id="app">
    <h1>Wallet</h1>
    <div>
      <p>Address: {{ account.address }}</p>
      <p>To: <input type="text" v-model="address" size="60" /></p>
      <p>Amount: <input type="text" v-model="amount" size="20" /></p>
      <button @click="submit">Submit</button>
      <pre v-if="message">{{ message }}</pre>
    </div>
  </div>
`;

const app = new Vue({
  data: {
    message: '',
    account,
    address: '0x6f0b7120bd7ea2a2fb3b3547d2998228c1fe60688748bc',
    amount: 1,
  },
  methods: {
    async submit() {
      this.message = '';
      try {
        const response = await sendTransaction({
          version: '0.5',
          type: 'SendCoin',
          from: account.address,
          to: this.address,
          amount: this.amount,
          fee: 0.0001,
          timestamp: (new Date()).valueOf() * 1000,
        }, account);
        const { data } = response;
        this.message = `TxID: ${data.txid}\nMessage: ${data.result}`;
      } catch (e) {
        this.message = `[ ERROR! ] ${e}`;
        console.log(e);
      }
    },
  },
  template,
});

app.$mount('#app');
