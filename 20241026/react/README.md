# Shop Demo

## 참고 자료

- <https://ko.vite.dev/guide/>
- <https://github.com/ahastudio/til/tree/main/vite>
- <https://github.com/ahastudio/CodingLife/tree/main/20240314/react>

## 세팅

```bash
npm init -y

npm i -D vite @vitejs/plugin-react

npm i react react-dom

npm i -D @types/react @types/react-dom

touch vite.config.js

touch index.html

mkdir -p src/styles

touch src/main.tsx
touch src/App.tsx
touch src/styles/global.css
```

`vite.config.js` 파일

```javascript
import { defineConfig } from 'vite';

import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
```

`index.html` 파일

```html
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>Shop Demo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.tsx"></script>
  </body>
</html>
```

`src/main.tsx` 파일

```tsx
import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App';

import './styles/global.css';

const container = document.getElementById('root');

if (container) {
  ReactDOM.createRoot(container).render((
    <React.StrictMode>
      <App />
    </React.StrictMode>
  ));
}
```

`src/App.tsx` 파일

```tsx
export default function App() {
  return (
    <div>
      <h1>Hello, World!</h1>
    </div>
  );
}
```

`src/styles/global.css` 파일

```css
* {
  word-break: keep-all;
  word-wrap: break-word;
}

html {
  scroll-behavior: smooth;
  font-size: 62.5%;
}

body {
  font-family: 'Noto Sans KR', 'Apple SD Gothic', sans-serif;
  font-size: 1.6rem;
  line-height: 1.4;
  background: #FFF;
  color: #000;
}

img {
  max-width: 100%;
}

a {
  color: #333;

  &:hover {
    color: #555;
  }
}

h1 {
  font-size: 2.4rem;
  font-weight: 700;
}
```
