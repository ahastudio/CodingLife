# Solidity Example

- https://github.com/trufflesuite/ganache-cli
- http://truffleframework.com/docs/getting_started/migrations

## Test

    $ ganache-cli --port 8545 -e 1000000000
    $ truffle migrate --reset
    $ truffle test

## Deploy

    $ ACCOUNT_PASSWORD=password truffle migrate
