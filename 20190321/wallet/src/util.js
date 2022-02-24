import { Buffer } from 'buffer';
import Hash from 'sumi-hash';
import nacl from 'tweetnacl';
import axios from 'axios';

const http = axios.create({
  baseURL: process.env.API_BASE_URL || 'http://localhost:18080',
});

const { keyPair } = nacl.sign;

const sign = (data, privateKey) => {
  const { secretKey } = keyPair.fromSeed(Buffer.from(privateKey, 'hex'));
  const signature = nacl.sign.detached(Buffer.from(data), secretKey);
  return Buffer.from(signature).toString('hex');
};

const sendTransaction = async (transaction, { privateKey, publicKey }) => {
  const rawTransaction = JSON.stringify(transaction);
  const digest = Hash.sha256(rawTransaction);
  const signature = sign(digest, privateKey);
  const signedTransaction = {
    transaction: rawTransaction,
    public_key: publicKey,
    signature,
  };
  const { data } = await http.get('/transaction', {
    params: signedTransaction,
  });
  if (data.status === 'fail') {
    throw data.msg;
  }
  data.data.txid = digest;
  return data;
};

export {
  sign,
  sendTransaction,
};
