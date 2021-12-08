# Webpack 5 샘플

- <https://webpack.js.org/guides/getting-started/>
- <https://webpack.js.org/guides/development/>
- <https://webpack.js.org/loaders/babel-loader/>

※ 주의할 점:
`index.html` 파일의 위치가 `public` 폴더 아래로 바뀜.

## Webpack 기본 세팅

```bash
npm init -y
npm i -D webpack webpack-cli webpack-dev-server
```

`public/index.html` 파일 작성

```bash
mkdir -p public
touch public/index.html
```

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Webpack Demo</title>
</head>
<body>
  <p>Hi!</p>
</body>
</html>
```

개발 서버 실행

```bash
npx webpack serve --mode=development
```

웹 브라우저에서 확인: <http://localhost:8080/>

## JavaScript 파일 분리

`src/index.js` 파일 작성

```javascript
const element = document.getElementById('app');
element.innerHTML = `
  <p>Hello, world!</p>
`;
```

`public/index.html` 파일 수정

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Webpack Demo</title>
</head>
<body>
  <div id="app">
    Loading...
  </div>
  <script src="main.js"></script>
</body>
</html>
```

## Babel & JSX 세팅

```bash
npm i -D babel-loader @babel/core @babel/preset-env @babel/preset-react
```

`webpack.config.js` 파일 작성

```javascript
module.exports = {
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env', '@babel/preset-react'],
          },
        },
      },
    ],
  },
};
```

또는 `webpack.config.js`와 `babel.config.js` 파일을 분리할 수 있다.

`webpack.config.js` 파일 작성

```javascript
module.exports = {
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        use: 'babel-loader',
      },
    ],
  },
};
```

`babel.config.js` 파일 작성

```javascript
module.exports = {
  presets: [
    '@babel/preset-env',
    '@babel/preset-react',
  ],
};
```

## ESLint 세팅

“[JavaScript 프로젝트 시작하기](https://github.com/ahastudio/til/blob/main/javascript/20181212-setup-javascript-project.md)”
문서 참고.

```bash
npm i -D eslint

npx eslint --init
```

## Visual Studio Code 세팅

`.vscode/settings.json` 파일 작성

```bash
mkdir -p .vscode
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
    "trailing-spaces.trimOnSave": true
}
```
