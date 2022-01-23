# Vitest 테스트

- <https://github.com/ahastudio/CodingLife/tree/main/20211009/vite>
- <https://github.com/ahastudio/til/tree/main/vitest>

## 프로젝트 생성 및 의존성 설치

```bash
npm init -y

npm install --save-dev typescript
npx tsc --init

npm install react react-dom
npm install --save-dev @types/react @types/react-dom

npm install --save-dev vite @vitejs/plugin-react
npm install --save-dev vitest c8 jsdom
npm install --save-dev @testing-library/react @testing-library/jest-dom

npm install --save-dev eslint
npx eslint --init
```

## `tsconfig.json` 파일에 JSX 설정 추가

```json
{
  "compilerOptions": {
    // ...(중략)...
    "jsx": "preserve", // ← 앞에 붙은 한 줄 주석 제거
    // ...(후략)...
```

## `vite.config.ts` 파일 작성

```bash
touch vite.config.ts
```

```javascript
/* eslint-disable import/no-extraneous-dependencies */

/// <reference types="vitest" />

import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: ['./vitest.setup.ts'],
  },
});
```

## `vitest.setup.ts` 파일 작성

```bash
touch vitest.setup.ts
```

```javascript
// eslint-disable-next-line import/no-extraneous-dependencies
import '@testing-library/jest-dom';
```

## `.eslintrc.js` 파일에 `jest` 설정 추가

Jest를 쓰는 건 아니지만, `describe`, `it`, `expect` 등을 위해 설정 추가.

```javascript
module.exports = {
  env: {
    browser: true,
    es2021: true,
    jest: true, // ← 추가!
  },
  // ...(후략)...
};
```

## 테스트 실행

```bash
npx vitest

npx vitest --coverage
```

## 서버 실행

```bash
npx vite --port 8080
```

<http://localhost:8080/>
