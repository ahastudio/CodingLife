# Mock 없는 평범한 React 테스트 예제

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

## JSON Server 실행

```bash
mkdir data

touch data/db.json

npm i -D json-server

npx json-server --watch data/db.json
```
