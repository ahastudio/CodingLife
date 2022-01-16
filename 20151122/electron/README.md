# Electron

## 참고 자료

- <https://www.electronjs.org/>
- <https://www.electronjs.org/docs/latest/tutorial/quick-start/>
- <https://github.com/electron/electron-quick-start>
- [https://github.com/ahastudio/CodingLife/tree/main/20151116/angular](https://j.mp/3zZT7Ux)
- [https://coffeescript.org/](https://j.mp/3g9IF2A)

## Install dependencies

```bash
npm install
```

## Run web server

```bash
npx parcel serve index.html --port 8080

# OR

npm run dev
```

<http://localhost:8080/>

## Generate `main.js` file

```bash
npx coffee -o dist -c main.coffee

# OR

npm run compile
```

## Build static files

```bash
npx parcel build index.html --public-url "./"

# OR

npm run build
```

## Run Electron

```bash
npm start
```
