# MultiSig Wallet

- <https://soliditylang.org/>
- <https://docs.soliditylang.org/en/v0.8.11/contracts.html>
- <https://github.com/protofire/solhint>
- <https://github.com/protofire/solhint/blob/master/docs/rules.md>

- <https://github.com/trufflesuite/ganache>
- <https://github.com/trufflesuite/truffle>
- <https://github.com/trufflesuite/truffle/tree/master/packages/contract>
- <https://www.npmjs.com/package/@truffle/contract>
- <https://www.npmjs.com/package/@truffle/hdwallet-provider>

- <https://web3js.readthedocs.io/en/v1.7.0/web3-eth.html>
- <https://web3js.readthedocs.io/en/v1.7.0/web3-eth-contract.html>
- <https://web3js.readthedocs.io/en/v1.7.0/web3-utils.html>
- <https://github.com/indutny/bn.js>

- <https://docs.metamask.io/>
- <https://docs.metamask.io/guide/ethereum-provider.html>

## Install dependencies

```bash
npm i -D eslint nodemon

npm i -D ganache truffle

npm i @truffle/contract
```

## Compile contracts

```bash
npx truffle compile
```

## Run contract test

```bash
export HDWP_ENDPOINT=http://localhost:18545
export HDWP_MNEMONIC="$(cat mnemonic.txt)"
```

```bash
npx ganache -p 18545 -e 100000000 -m $HDWP_MNEMONIC
```

```bash
npx truffle test --network test

# OR

npm test
```

```bash
npm run watch:test
```

## Deploy contracts

```bash
export HDWP_ENDPOINT=https://ropsten.infura.io/v3/00000
export HDWP_MNEMONIC="$(cat mnemonic.txt)"
```

```bash
npx truffle migrate --network ropsten
```

Back up the `build` directory.

## Run web server

```bash
npm run serve
```

<http://localhost:3000/>
