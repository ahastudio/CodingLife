# React 기본 테스트 예제

Mock 없이 기본 코드를 작성하는 걸 보여주기 위해 `jest.mock` 같은 건 안 썼지만, 테스트용 API 서버를 띄우긴 함. 😉
([src/TestServer.ts](https://github.com/ahastudio/CodingLife/blob/main/20220713/react/src/TestServer.ts))

## 기본 세팅

```bash
npm init -y

touch .gitignore
touch .eslintignore

#

mkdir .vscode

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/.vscode/settings.json \
    -o .vscode/settings.json

#

npm i -D typescript

npx tsc --init

#

npm i -D eslint

npx eslint --init

#

npm i -D jest @types/jest @swc/core @swc/jest \
    jest-environment-jsdom \
    @testing-library/react @testing-library/jest-dom

touch jest.config.js

#

npm i react react-dom
npm i -D @types/react @types/react-dom

#

npm i react-redux @reduxjs/toolkit

#

npm i -D parcel
```

## API 서버 실행

테스트와 별개로 웹 브라우저에서 확인하려면 JSON Server를 실행해야 합니다.

```bash
mkdir data

touch data/db.json

npm i -D json-server

npx json-server --watch data/db.json
```
