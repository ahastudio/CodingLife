# RTK Query 예제

- <https://redux-toolkit.js.org/tutorials/rtk-query>
- <https://redux.js.org/tutorials/essentials/part-7-rtk-query-basics>
- <https://github.com/reduxjs/redux-toolkit/tree/master/examples/query/react/basic>
- <https://babeljs.io/docs/en/usage>
- <https://jestjs.io/docs/getting-started>
- <https://www.npmjs.com/package/whatwg-fetch>
- <https://github.com/lukejacksonn/servor>
- <https://github.com/ahastudio/CodingLife/tree/main/20211208/react>
- <https://github.com/ahastudio/CodingLife/tree/main/20211008/react>
- <https://github.com/ahastudio/CodingLife/tree/main/20220108/msw>

```bash
npm init -y

npm i -D webpack webpack-cli webpack-dev-server

npm i -D babel-loader @babel/core @babel/preset-env @babel/preset-react

npm i react react-dom

npm i -D @types/react @types/react-dom

npm i react-redux @reduxjs/toolkit

npm i -D @types/react-redux

npm i -D jest babel-jest @types/jest \
    @testing-library/react @testing-library/jest-dom

npm i -D msw whatwg-fetch regenerator-runtime servor

npm install --save-dev eslint

npx eslint --init
```

```bash
npx webpack serve --mode development
```

<http://localhost:8080/>

```bash
# Run development server
npm start

# Lint and fix all
npm run lint

# Run tests
npm test

# Watch and run tests
npm test -- --watchAll

# Generate static files
npm run build

# Run web server to serve static files
npm run serve
```
