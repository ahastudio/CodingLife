import * as _ from 'lodash';
import dayjs from 'dayjs';

import {
  createAccount,
  sendTransaction,
  sendRequest,
  getBalance,
  getTransactions,
} from 'saseul';

const account = {
  privateKey: process.env.PRIVATE_KEY,
  publicKey: process.env.PUBLIC_KEY,
  address: process.env.ADDRESS,
};

const sendCoin = ({ to, amount }) => sendTransaction({
  version: '0.5',
  type: 'SendCoin',
  from: account.address,
  to,
  amount,
  fee: 0,
  timestamp: (new Date()).valueOf() * 1000,
}, account);

const sendToken = ({ tokenName, to, amount }) => sendTransaction({
  version: '0.5',
  type: 'SendToken',
  token_name: tokenName,
  from: account.address,
  to,
  amount,
  fee: 0,
  timestamp: (new Date()).valueOf() * 1000,
}, account);

const getTokens = async () => {
  const request = {
    version: '0.5',
    type: 'GetTokenBalance',
    from: account.address,
    timestamp: dayjs().valueOf() * 1000,
  };

  const tokens = await sendRequest(request, account);

  return _.map(tokens, (amount, name) => ({ name, amount }));
};

const template = `
  <div id="app">
    <h1>Wallet</h1>
    <div>
      <p>Address: {{ account.address }}</p>
      <p>Coin Balance: {{ balance }}</p>
      <p v-for="token in tokens">
        Token {{ token.name }}: {{ token.amount }}
      </p>
    </div>
    <div>
      <hr>
      <h2>Send Coin or Token</h2>
      <p>
        Currency:
        <select v-model="currency">
          <option value="">Coin</option>
          <option v-for="token in tokens" :value="token.name">Token {{ token.name }}</option>
        </select>
      </p>
      <p>To: <input type="text" v-model="address" size="60" /></p>
      <p>Amount: <input type="text" v-model="amount" size="20" /></p>
      <button @click="submit">Submit</button>
      <pre v-if="message">{{ message }}</pre>
    </div>
    <div>
      <hr>
      <h2>Transactions</h2>
      <p v-for="tx in transactions">
        TxID: {{tx.thash}}
        <br>
        From: {{ tx.transaction.from }}
        <span v-if="tx.transaction.from === account.address">(me!)</span>
        <br>
        To: {{ tx.transaction.to }}
        <span v-if="tx.transaction.to === account.address">(me!)</span>
        <br>
        Amount: {{ tx.transaction.amount }}
      </p>
    </div>
    <div>
      <hr>
      <h2>[BONUS] Create New Account</h2>
      <button @click="createNewAccount">Generate</button>
      <pre>{{ newAccount }}</pre>
    </div>
  </div>
`;

const app = new Vue({
  data: {
    message: '',
    account,
    balance: 0,
    tokens: [],
    currency: '',
    address: '0x6ff5b6d70202af60367c099f16af83e136d2e9a350335d',
    amount: 1,
    transactions: [],
    newAccount: '',
  },
  mounted() {
    this.update();
    setInterval(() => {
      this.update();
    }, 1000);
  },
  methods: {
    async update() {
      try {
        const { balance } = await getBalance(account);
        this.balance = balance;
        this.tokens = await getTokens();
        this.transactions = await getTransactions(account);
      } catch (e) {
        console.error(e);
      }
    },
    async submit() {
      this.message = '';
      const f = this.currency ? sendToken : sendCoin;
      const params = {
        tokenName: this.currency,
        to: this.address,
        amount: this.amount,
      };
      try {
        const { thash } = await f(params);
        this.message = `[ SUBMIT ] TxID: ${thash}`;
      } catch (e) {
        this.message = `[ ERROR! ] ${e}`;
      }
    },
    createNewAccount() {
      const newAccount = createAccount();
      this.newAccount = JSON.stringify(newAccount, null, 4);
    },
  },
  template,
});

app.$mount('#app');
