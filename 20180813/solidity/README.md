# ERC-20 Token Example

## Install global packages

```
$ yarn global add truffle ganache-cli
```

## Generate a new project

```
$ truffle init
$ yarn init
$ yarn add eslint
$ node_modules/.bin/eslint --init
$ node_modules/.bin/eslint . --fix
```

## Generate a new token

```
$ yarn add openzeppelin-solidity
$ truffle create contract MyToken
$ truffle create test MyToken
$ mv test/my_token.js test/my_token.test.js
```

## Test

```
$ ganache-cli
```

```
$ truffle test
```
