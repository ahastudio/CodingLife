# Vite 테스트

- <https://github.com/ahastudio/CodingLife/tree/main/20211008/react>
- <https://github.com/vitejs/vite>
- <https://github.com/vitejs/vite/tree/main/packages/plugin-react>

## 프로젝트 생성 및 의존성 설치

```bash
npm init -y

npm install --save-dev eslint
npx eslint --init

npm install --save-dev typescript
npx tsc --init

npm install react react-dom
npm install --save-dev @types/react @types/react-dom

npm install --save-dev vite @vitejs/plugin-react
```

## `vite.config.js` 파일 작성

```bash
touch vite.config.js
```

```javascript
/* eslint-disable import/no-extraneous-dependencies */

import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
```

## 서버 실행

```bash
npx vite --port 8080
```

<http://localhost:8080/>
