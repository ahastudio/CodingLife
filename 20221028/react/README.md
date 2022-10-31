# JavaScript + React + usestore-ts 예제

참고:
<https://github.com/ahastudio/CodingLife/tree/main/20220726/react>

## 프로젝트 생성

```bash
npm init -y
```

## Git과 ESLint를 위한 무시 파일 생성

```bash
touch .gitignore
touch .eslintignore
```

두 파일 모두 다음과 같이 작성한다.

```txt
/node_modules/
/coverage/
/dist/
.parcel-cache
```

## Visual Studio Code 세팅

기존 파일을 그대로 다운로드헤서 사용한다.

```bash
mkdir .vscode

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/.vscode/settings.json \
    -o .vscode/settings.json
```

## Decoreator 세팅

```bash
touch jsconfig.json
```

```json
{
  "compilerOptions": {
    "experimentalDecorators": true
  }
}
```

## ESLint 세팅

Decorator 지원을 위해 ESLint에서 Babel을 활용.

```bash
npm i -D eslint @babel/eslint-parser \
    @babel/preset-react @babel/plugin-proposal-decorators

npx eslint --init
```

`.eslintrc.js` 파일은 적절히 수정한다.

```js
  extends: [
    'airbnb',
    'plugin:react/recommended',
    'plugin:react/jsx-runtime',
  ],
```

```js
  parser: '@babel/eslint-parser',
  parserOptions: {
    requireConfigFile: false,
    babelOptions: {
      presets: ['@babel/preset-react'],
      plugins: [
        [
          '@babel/plugin-proposal-decorators',
          { decoratorsBeforeExport: true },
        ],
      ],
    },
    legacyDecorators: true,
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
```

```js
  rules: {
    indent: ['error', 2],
    'no-trailing-spaces': 'error',
    curly: 'error',
    'brace-style': 'error',
    'no-multi-spaces': 'error',
    'space-infix-ops': 'error',
    'space-unary-ops': 'error',
    'no-whitespace-before-property': 'error',
    'func-call-spacing': 'error',
    'space-before-blocks': 'error',
    'keyword-spacing': ['error', { before: true, after: true }],
    'comma-spacing': ['error', { before: false, after: true }],
    'comma-style': ['error', 'last'],
    'comma-dangle': ['error', 'always-multiline'],
    'space-in-parens': ['error', 'never'],
    'block-spacing': 'error',
    'array-bracket-spacing': ['error', 'never'],
    'object-curly-spacing': ['error', 'always'],
    'key-spacing': ['error', { mode: 'strict' }],
    'arrow-spacing': ['error', { before: true, after: true }],
  },
```

## Jest와 SWC로 테스트 환경 구축

관련 패키지 설치.

```bash
npm i -D jest @types/jest @swc/core @swc/jest \
    jest-environment-jsdom \
    @testing-library/react @testing-library/jest-dom
```

Jest 환경 설정 파일 생성.

```bash
touch jest.config.js
```

`jest.config.js` 파일 작성.
React와 Decorator를 지원하도록 설정한다.

```js
module.exports = {
  testEnvironment: 'jsdom',
  setupFilesAfterEnv: [
    '@testing-library/jest-dom/extend-expect',
  ],
  transform: {
    '^.+\\.jsx?$': ['@swc/jest', {
      jsc: {
        parser: {
          jsx: true,
          decorators: true,
        },
        transform: {
          react: {
            runtime: 'automatic',
          },
          legacyDecorator: true,
          decoratorMetadata: true,
        },
      },
    }],
  },
};
```

## React 설치

```bash
npm i react react-dom

npm i -D @types/react @types/react-dom
```

## Parcel 설치

```bash
npm i -D parcel
```

## usestore-ts 설치

```bash
npm i usestore-ts
```
