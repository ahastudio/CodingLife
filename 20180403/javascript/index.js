// https://en.wikipedia.org/wiki/EdDSA
// http://ed25519.cr.yp.to/
// https://github.com/dchest/tweetnacl-js

const nacl = require('tweetnacl');

function toHex(data) {
  return Array.from(data)
              .map(i => ('0' + i.toString(16))
              .slice(-2)).join('').toUpperCase();
}

const key = nacl.sign.keyPair();
const pubKey = toHex(key.publicKey);
const privKey = toHex(key.secretKey);

console.log(pubKey);
console.log(pubKey.length);

console.log(privKey);
console.log(privKey.length);

// https://github.com/crypto-browserify/ripemd160

const RIPEMD160 = require('ripemd160');
const hash = new RIPEMD160().update(pubKey).digest('hex').toUpperCase();

console.log(hash);
console.log(hash.length);
