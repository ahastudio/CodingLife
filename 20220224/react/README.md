# RECOIL-NEXUS 예제

참고:

- <https://github.com/ahastudio/til/blob/main/react/recoil.md>
- <https://github.com/ahastudio/CodingLife/tree/main/20211012/react>
- <https://github.com/ahastudio/CodingLife/tree/main/20220124/react-swc>

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

npm i recoil-nexus

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
