# React 프로젝트 예제

기존 코드:
<https://github.com/ahastudio/CodingLife/tree/main/20211008/react>

## Parcel 대신 esbuild 사용

- <https://github.com/evanw/esbuild>
- <https://github.com/lukejacksonn/servor>
- <https://github.com/open-cli-tools/concurrently>

```bash
rm -rf .parcel-cache/

npm uninstall parcel

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
