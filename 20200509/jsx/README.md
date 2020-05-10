# JSX Example

- [JavaScript 프로젝트 시작하기](https://j.mp/2AnJx1x)
- [JavaScript Sample Project](https://j.mp/2AkJkfA)
- [JSX 간단 테스트 예제](https://j.mp/2VfjamM)

## NPM 프로젝트 만들기

```bash
npm init -y
```

## ESLint 설치 및 설정

```bash
npm i -D eslint

npx eslint --init
```

## Webpack Dev Server 설치

```bash
npm i -D webpack webpack-cli webpack-dev-server
npm i -D babel-loader html-webpack-plugin

touch webpack.config.js
```

## Babel 설치

```bash
npm i -D @babel/core @babel/preset-env @babel/preset-react

touch babel.config.js
```

## Webpack Dev Server 실행

```bash
npx webpack-dev-server
```

<http://localhost:8080/>

```bash
touch index.html

mkdir -p src

touch src/index.jsx
```
