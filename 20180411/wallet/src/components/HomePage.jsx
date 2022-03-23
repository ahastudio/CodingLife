import React, { useEffect, useState } from 'react';

import { BigNumber } from 'bignumber.js';
import moment from 'moment';
import axios from 'axios';
import nacl from 'tweetnacl';
import Hash from 'sumi-hash';

const { log, error } = console;

function toHex(data) {
  return Array.from(data)
    .map((i) => (`0${i.toString(16)}`)
      .slice(-2)).join('').toUpperCase();
}

function fromHex(hex, bytes) {
  const data = bytes ? ('0'.repeat(bytes * 2) + hex).slice(-bytes * 2) : hex;
  const r = Array(data.length / 2).fill().map((_, i) => i * 2);
  const a = r.map((i) => parseInt(data.slice(i, i + 2), 16));
  return Uint8Array.from(a);
}

function concatenate(resultConstructor, ...arrays) {
  const length = arrays.map((i) => i.length).reduce((a, b) => a + b);
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
  const data = {
    jsonrpc: '2.0', id, method, params,
  };
  const config = {
    headers: { 'content-type': 'application/json' },
  };
  const response = await axios.post(url, data, config);
  return response.data;
}

function rawTxData(transaction) {
  const { timestamp, from, to } = transaction;
  const amount = new BigNumber(transaction.amount);
  return concatenate(
    Uint8Array,
    fromHex(timestamp.toString(16), 4).reverse(),
    fromHex(from, 20),
    fromHex(to, 20),
    fromHex(amount.toString(16), 32).reverse(),
  );
}

async function callSendTransaction(transaction) {
  const {
    timestamp, from, to, amount, signature, publicKey,
  } = transaction;

  try {
    const data = await callMethod('send_transaction', {
      timestamp, from, to, amount, signature, publicKey,
    });
    if (data.error) {
      error(data.error);
      return null;
    }
    log(data.result);

    return {
      ...transaction,
      hash: data.result.hash,
    };
  } catch (e) {
    error(e);
    return null;
  }
}

export default function HomePage() {
  const [state, setState] = useState({
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
  });

  useEffect(() => {
    setState({
      ...state,
      accounts: JSON.parse(localStorage.getItem('accounts') || '[]'),
    });
  }, []);

  const newAccount = () => {
    const key = nacl.sign.keyPair();
    const publicKey = String.fromCharCode.apply(null, key.publicKey);
    const accounts = [
      ...state.accounts,
      {
        privateKey: toHex(key.secretKey),
        publicKey: toHex(key.publicKey),
        address: Hash.rmd160(publicKey).toUpperCase(),
      },
    ];
    setState({
      ...state,
      accounts,
    });
    localStorage.setItem('accounts', JSON.stringify(accounts));
  };

  const selectAccount = (account) => {
    setState({
      ...state,
      account,
      transaction: {
        ...state.transaction,
        from: account.address,
      },
    });
  };

  const handleChangeTransaction = (event) => {
    const { name, value } = event.target;
    setState({
      ...state,
      transaction: {
        ...state.transaction,
        [name]: value,
      },
    });
  };

  const sendTx = async () => {
    const transaction = {
      ...state.transaction,
      timestamp: moment().unix(),
    };

    const data = rawTxData(transaction);

    const privateKey = fromHex(state.account.privateKey);
    const signature = nacl.sign.detached(data, privateKey);

    const hashedTransaction = await callSendTransaction({
      ...transaction,
      signature: toHex(signature),
      publicKey: state.account.publicKey,
    });

    if (!hashedTransaction) {
      return;
    }

    setState({
      ...state,
      transaction: {
        ...state.transaction,
        hash: '',
        timestamp: '',
        signature: '',
        confirmed: false,
      },
      rawTransaction: toHex(data),
      transactions: [
        ...state.transactions,
        hashedTransaction,
      ],
    });
  };

  return (
    <div className="home-page">
      <div className="accounts">
        <h2>Accounts</h2>
        <ol>
          {state.accounts.map((account) => (
            <li key={account.privateKey}>
              <button
                type="button"
                onClick={() => selectAccount(account)}
              >
                {account.address}
              </button>
            </li>
          ))}
        </ol>
        <button type="button" onClick={newAccount}>New Account</button>
      </div>
      {state.account && (
        <div>
          <dl>
            <dt>Address</dt>
            <dd>{state.account.address}</dd>
            <dt>Public Key</dt>
            <dd>{state.account.publicKey}</dd>
            <dt>Private Key</dt>
            <dd>{state.account.privateKey}</dd>
          </dl>
          <div>
            <h2>Send Transaction</h2>
            <div>
              From:
              <input
                type="text"
                name="from"
                value={state.transaction.from}
                onChange={handleChangeTransaction}
              />
            </div>
            <div>
              To:
              <input
                type="text"
                name="to"
                value={state.transaction.to}
                onChange={handleChangeTransaction}
              />
            </div>
            <div>
              Amount:
              <input
                type="number"
                name="amount"
                value={state.transaction.amount}
                onChange={handleChangeTransaction}
              />
            </div>
            <button
              type="button"
              onClick={sendTx}
            >
              Send
            </button>
          </div>
          <div>
            {state.rawTransaction}
          </div>
        </div>
      )}
      <div>
        <h2>
          Transactions
          {' '}
          (
          {state.transactions.length}
          )
        </h2>
        <ol>
          {state.transactions.map((transaction) => (
            <li key={transaction.hash}>
              {JSON.stringify(transaction)}
            </li>
          ))}
        </ol>
      </div>
    </div>
  );
}
