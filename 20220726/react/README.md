# `usestore-ts` 예제

`usestore-ts`:
[https://usestore-ts.com/](https://bit.ly/3zoPgmI)

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

## TypeScript 세팅

```bash
npm i -D typescript

npx tsc --init
```

JSX를 사용하기 위해 `tsconfig.json` 파일의 `jsx` 주석을 풀어준다.
Decorator를 사용하기 위해 `decorators` 관련 주석도 풀어준다.

```json
    "jsx": "preserve",
    "experimentalDecorators": true,
    "emitDecoratorMetadata": true,
```

## ESLint 세팅

```bash
npm i -D eslint

npx eslint --init
```

`.eslintrc.js` 파일은 적절히 수정한다.

```js
  extends: [
    'airbnb',
    'plugin:@typescript-eslint/recommended',
    'plugin:react/recommended',
    'plugin:react/jsx-runtime',
  ],
```

```js
  settings: {
    'import/resolver': {
      node: {
        extensions: ['.js', '.jsx', '.ts', '.tsx'],
      },
    },
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
    'import/no-extraneous-dependencies': ['error', {
      devDependencies: [
        '**/*.test.js',
        '**/*.test.jsx',
        '**/*.test.ts',
        '**/*.test.tsx',
      ],
    }],
    'import/extensions': ['error', 'ignorePackages', {
      js: 'never',
      jsx: 'never',
      ts: 'never',
      tsx: 'never',
    }],
    'react/jsx-filename-extension': [2, {
      extensions: ['.js', '.jsx', '.ts', '.tsx'],
    }],
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
`usestore-ts` 패키지도 변환하도록
`transformIgnorePatterns` 옵션을 설정한다.

```js
module.exports = {
  testEnvironment: 'jsdom',
  setupFilesAfterEnv: [
    '@testing-library/jest-dom/extend-expect',
  ],
  transform: {
    '^.+\\.(t|j)sx?$': ['@swc/jest', {
      jsc: {
        parser: {
          syntax: 'typescript',
          jsx: true,
        },
        transform: {
          react: {
            runtime: 'automatic',
          },
        },
      },
    }],
  },
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
    '<rootDir>/dist/',
  ],
  transformIgnorePatterns: [
    'node_modules/(?!usestore-ts)',
  ],
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
