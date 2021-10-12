# React 프로젝트 예제

기존 코드:
<https://github.com/ahastudio/CodingLife/tree/main/20211008/react>

## Parcel 대신 esbuild 사용

- <https://github.com/evanw/esbuild>
- <https://github.com/lukejacksonn/servor>
- <https://github.com/open-cli-tools/concurrently>

Parcel 제거:

```bash
npm uninstall parcel

rm -rf .parcel-cache/
```

esbuild, Servør, Concurrently 설치:

```bash
npm install --save-dev esbuild servor concurrently
```

`tsconfig.json` 파일 수정:

```json
{
  // ...(전략)...
    "target": "es6",
  // ...(후략)...
}
```

`react-shim.js` 파일 작성:

```javascript
/* eslint-disable import/prefer-default-export */

import * as React from 'react';

export { React };
```

`package.json` 파일 수정:

```json
{
  "scripts": {
    "start": "npm run build && concurrently 'npm:serve' 'npm:watch-js'",
    "serve": "servor dist index.html 8080 --reload",
    "build": "mkdir -p dist && cp index.html dist/ && npm run build-js",
    "build-js": "esbuild ./src --bundle --inject:./react-shim.js --outfile=dist/bundle.js",
    "watch-js": "npm run build-js -- --watch",
    // ...(후략)...
  }
}
```

## styled-components 사용

[styled-components](https://github.com/styled-components/styled-components)
설치.

```bash
npm install styled-components
npm install --save-dev @types/styled-components
```

Visual Studio Code에
“[vscode-styled-components](https://j.mp/3AzM3hF)”
Extension 설치.

설치 후 화면:

<img src="https://github.com/styled-components/vscode-styled-components/raw/HEAD/demo.gif" alt="demo" width="617" />

`src/App.tsx` 수정:

```tsx
import styled from 'styled-components';

const Greeting = styled.p`
  font-size: 2em;
  text-align: center;
  i {
    font-size: 5em;
  }
`;

export default function App() {
  return (
    <Greeting>
      Hello, world
      <i>!</i>
    </Greeting>
  );
}
```
