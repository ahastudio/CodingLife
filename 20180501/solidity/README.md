# Solidity Example

- <https://github.com/trufflesuite/ganache-cli>
- <http://truffleframework.com/docs/getting_started/migrations>

## Test

```bash
ganache-cli --port 8545 -e 1000000000
```

```bash
truffle migrate --reset
truffle test
```

## Deploy

```bash
ACCOUNT_PASSWORD=password truffle migrate
```
