# React + Apollo Client 캐시 예제

- <https://github.com/ahastudio/CodingLife/tree/main/20211008/react>

## npm 프로젝트 생성

```bash
mkdir <프로젝트>
cd <프로젝트>
```

```bash
npm init -y
```

## `.gitignore` 준비

```bash
touch .gitignore
```

`.gitignore` 파일 작성.

```txt
/node_modules/
/dist/
/.parcel-cache/
```

## `.eslintignore` 준비

```bash
touch .eslintignore
```

`.eslintignore` 파일 작성.

```txt
/node_modules/
/dist/
/.parcel-cache/
```

## `package.json` 스크립트 설정

```json
{
  // ...(전략)...
  "scripts": {
    "start": "parcel index.html --port 8080",
    "build": "parcel build index.html",
    "check": "tsc --noEmit",
    "lint": "eslint --fix --ext .js,.jsx,.ts,.tsx .",
    "test": "jest"
  },
  // ...(후략)...
}
```

## `.vscode` 세팅

```bash
mkdir .vscode
touch .vscode/settings.json
```

`.vscode/settings.json` 파일 작성

```json
{
    "editor.rulers": [
        80
    ],
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
    },
    "trailing-spaces.trimOnSave": true
}
```

## TypeScript 세팅

```bash
npm install --save-dev typescript

npx tsc --init
```

`tsconfig.json` 파일 수정.

```json
{
  "compilerOptions": {
    // ...(전략)...
    "jsx": "react-jsx",
    // ...(후략)...
  }
}
```

## ESLint 세팅

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

`.eslintrc.js` 파일 수정.

통째로 베끼기:
<https://github.com/ahastudio/CodingLife/blob/main/20211008/react/.eslintrc.js>

## Jest 세팅

```bash
npm install --save-dev jest ts-jest @types/jest \
    @testing-library/react @testing-library/jest-dom

touch jest.config.js
```

`jest.config.js` 파일 작성.

```js
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

## React 설치

```bash
npm install react react-dom
npm install --save-dev @types/react @types/react-dom
```

### React 기본 코드 작성

```bash
mkdir src
touch src/index.tsx
touch src/App.tsx
touch src/App.test.tsx
```

`src/App.tsx` 파일 작성.

```tsx
export default function App() {
  return null;
}
```

`src/App.test.tsx` 파일 작성.

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

테스트 실행하면 실패.

```bash
npx jest
```

`src/App.tsx` 파일 수정.

```tsx
export default function App() {
  return (
    <p>Hello, world!</p>
  );
}
```

테스트 다시 실행하면 성공.

```bash
npx jest
```

`src/index.tsx` 파일 작성.

```tsx
import * as ReactDOM from 'react-dom';

import App from './App';

ReactDOM.render(<App />, document.getElementById('app'));
```

## Parcel 세팅

```bash
npm install --save-dev parcel
```

`index.html` 파일 작성.

```bash
touch index.html
```

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

웹 서버 실행.

```bash
npx parcel index.html --port 8080
```

<http://localhost:8080/>
