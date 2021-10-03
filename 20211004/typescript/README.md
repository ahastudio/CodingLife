# TypeScript + React sample

<https://github.com/kulshekhar/ts-jest>

## Install dependencies

```bash
npm install react react-dom

npm install --save-dev parcel typescript eslint jest ts-jest \
    @types/jest @types/react @types/react-dom \
    @testing-library/react @testing-library/jest-dom
```

## Check TypeScript code

```bash
npx tsc --noEmit

# OR

npm run check
```

## Lint and fix all

```bash
npx eslint --fix --ext .js,.jsx,.ts,.tsx src

# OR

npm run lint
```

## Run tests

```bash
npx jest

# OR

npm test
```

## Run web server for development

```bash
npx parcel index.html --port 8080

# OR

npm start
```

<http://localhost:8080/>
