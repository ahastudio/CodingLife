# React + TypeScript + Parcel

## 실행만 해보기

```bash
npm ci

npm test

npm start
```

웹 브라우저로 확인: <http://localhost:8080/>

## 처음부터 따라하기

아래 내용은 이 프로젝트를 참고하되, 빈 폴더에서 진행하셔야 합니다.

기본적인 설정은
“[JavaScript 프로젝트 시작하기](https://j.mp/3FCU7C7)”
문서를 참고하세요.

### NPM 프로젝트 생성

```bash
npm init -y
```

### TypeScript 세팅

`typescript` 패키지를 설치하고,
`tsconfig.json` 파일을 자동으로 생성합니다.

```bash
npm install --save-dev typescript

npx tsc --init
```

`package.json` 파일에 의존성이 추가된 걸 확인할 수 있습니다.

```json
{
  "devDependencies": {
    "typescript": "^4.4.3"
  }
}
```

JSX를 사용하기 위해 `tsconfig.json` 파일을 열어
옵션을 하나 수정합니다.

```json
{
  "compilerOptions": {
    // ...(전략)...
    "jsx": "react-jsx",
    // ...(후략)...
  }
}
```

TypeScript 컴파일을 통해 문법 오류를 확인하는
`check` 명령을 `package.json` 파일에 추가합니다.

```json
{
  "scripts": {
    "check": "tsc --noEmit"
  }
}
```

다음 명령을 입력해서 문법 오류가 있습니다.
이 명령을 자주 실행하고, CI 등에 포함시킵니다.

```bash
npm run check
```

### ESLint 세팅

```bash
npm install --save-dev eslint

npx eslint --init
```

```txt
? How would you like to use ESLint? …
❯ To check syntax, find problems, and enforce code style

? What type of modules does your project use? …
❯ JavaScript modules (import/export)

? Which framework does your project use? …
❯ React

? Does your project use TypeScript?
› Yes

? Where does your code run? …
✔ Browser

? How would you like to define a style for your project? …
❯ Use a popular style guide

? Which style guide do you want to follow? …
❯ Airbnb: https://github.com/airbnb/javascript

? What format do you want your config file to be in? …
❯ JavaScript

? Would you like to install them now with npm?
› Yes
```

`package.json` 파일에 포함된 의존성:

```json
{
  "devDependencies": {
    // ...(전략)...
    "@typescript-eslint/eslint-plugin": "^4.33.0",
    "@typescript-eslint/parser": "^4.33.0",
    "eslint": "^7.32.0",
    "eslint-config-airbnb": "^18.2.1",
    "eslint-plugin-import": "^2.24.2",
    "eslint-plugin-jsx-a11y": "^6.4.1",
    "eslint-plugin-react": "^7.26.1",
    "eslint-plugin-react-hooks": "^4.2.0",
    // ...(후략)...
  }
}
```

`.eslintrc.js` 파일에 설정 추가:

```javascript
module.exports = {
  // ...(전략)...
  extends: [
    'airbnb',
    'plugin:react/recommended',
    'plugin:react/jsx-runtime',
  ],
  // ...(중략)...
  settings: {
    'import/resolver': {
      node: {
        extensions: ['.js', '.jsx', '.ts', '.tsx'],
      },
    },
  },
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
};
```

`pacakge.json` 파일에 `lint` 명령 추가:

```json
{
  "scripts": {
    // ...(전략)...
    "lint": "eslint --fix --ext .js,.jsx,.ts,.tsx src"
  }
}
```

Lint and fix all:

```bash
npm run lint
```

### React 설치

```bash
npm install react react-dom
npm install --save-dev @types/react @types/react-dom
```

### React 기본 코드 작성

```bash
mkdir src
touch src/index.tsx
touch src/App.tsx
```

`src/App.tsx` 파일:

```tsx
export default function App() {
  return (
    <p>Hello, world!</p>
  );
}
```

`src/index.tsx` 파일:

```tsx
import * as ReactDOM from 'react-dom';

import App from './App';

const container = document.getElementById('app');
ReactDOM.render(<App />, container);
```

## Jest 세팅

```bash
npm install --save-dev jest ts-jest @types/jest \
    @testing-library/react @testing-library/jest-dom
```

`jest.config.js` 파일 작성:

```javascript
module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'jsdom',
  setupFilesAfterEnv: [
    '@testing-library/jest-dom/extend-expect',
  ],
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
    '<rootDir>/dist/',
  ],
};
```

`.eslintrc.js` 파일에 설정 추가:

```javascript
module.exports = {
  env: {
    // ...(전략)...
    jest: true,
  },
  // ...(후략)...
};
```

`src/App.test.tsx` 파일 작성:

```tsx
import { render } from '@testing-library/react';

import App from './App';

describe('App', () => {
  it('renders greeting message', () => {
    const { container } = render(<App />);

    expect(container).toHaveTextContent('Hello, world!');
  });
});
```

`pacakge.json` 파일에 `test` 명령 추가:

```json
{
  "scripts": {
    // ...(전략)...
    "test": "jest"
  }
}
```

Run tests:

```bash
npm test
```

### Parcel 설치

```bash
npm install --save-dev parcel
```

`pacakge.json` 파일에 `start` 명령 추가:

```json
{
  "scripts": {
    "start": "parcel index.html --port 8080",
    // ...(후략)...
  }
}
```

`.gitignore` 내용 추가:

```txt
/.parcel-cache/
/dist/
```

`index.html` 파일 작성:

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Sample</title>
</head>
<body>
  <div id="app">
    Loading...
  </div>
  <script type="module" src="./src/index.tsx"></script>
</body>
</html>
```

Run web server for development:

```bash
npm start
```

웹 브라우저로 확인: <http://localhost:8080/>

### (BONUS) Visual Studio Code 세팅

`.vscode/settings.json` 파일에 설정 추가.

```bash
mkdir .vscode
touch .vscode/settings.json
```

```json
{
    "editor.rulers": [
        80
    ],
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
    },
    "trailing-spaces.trimOnSave": true,
}
```
