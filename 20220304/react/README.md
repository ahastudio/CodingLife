# React Context & Recoil 예제

React 외부에 (mutable) Service를 만들고, Recoil로 핵심 상태를 구독.

참고:

- <https://github.com/ahastudio/CodingLife/tree/main/20220224/react>
- <https://github.com/dal-lab/frontend-tdd-examples/tree/main/2-todo-app>
- <https://beta.reactjs.org/learn/passing-data-deeply-with-context>

## Set up

```bash
npm init -y

touch .gitignore
touch .eslintignore

mkdir .vscode
touch .vscode/settings.json

npm i -D typescript

npx tsc --init

npm i -D eslint

npx eslint --init

npm i -D jest @types/jest @swc/core @swc/jest \
    @testing-library/react @testing-library/jest-dom

touch jest.config.js

npm i react react-dom
npm i -D @types/react @types/react-dom

npm i recoil
npm i -D @types/recoil

npm i -D parcel

touch index.html

mkdir -p src/components

touch src/index.tsx
touch src/App.tsx
touch src/App.test.tsx
touch src/components/Main.tsx
touch src/components/Main.test.tsx
```

## Run web server

```bash
npm start
```

<http://localhost:3000/>
