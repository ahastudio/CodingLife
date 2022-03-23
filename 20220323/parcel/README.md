# Parcel 예제

참고:

- [https://github.com/ahastudio/til/blob/main/javascript/20181212-setup-javascript-project.md](https://bit.ly/3FCU7C7)
- [https://github.com/ahastudio/CodingLife/tree/main/20211008/react](https://bit.ly/3JELYhW)

## 프로젝트 생성

```bash
mkdir my-project
cd my-project

npm init -y
```

## ignore 파일 작성

```bash
touch .gitignore
touch .eslintignore
```

`.gitignore` 파일과 `.eslintignore` 파일 모두 같은 내용.

```txt
/node_modules/
/dist/
/.parcel-cache/
```

## Visual Studio Code 세팅

```bash
mkdir .vscode
touch .vscode/settings.json
```

`.vscode/settings.json`

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

## ESLint 세팅

```bash
npm i -D eslint

npx eslint --init
```

## HTML 파일 작성

```bash
touch index.html
```

`index.html`

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
    <script type="module" src="./src/index.js"></script>
  </body>
</html>
```

## JavaScript 코드 작성

```bash
mkdir src
touch src/index.js
```

`src/index.js`

```js
const element = document.getElementById('app');
element.innerHTML = `
  <p>Hello, world!</p>
`;
```

## Parcel 세팅

```bash
npm i -D parcel
```

## 웹 서버 실행

```bash
npx parcel index.html -p 8080
```

<http://localhost:8080/>
