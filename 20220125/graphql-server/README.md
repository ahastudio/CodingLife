# GraphQL Subscription Server

- <https://github.com/ahastudio/CodingLife/tree/main/20211216/graphql>
- <https://www.apollographql.com/docs/apollo-server/integrations/middleware/#apollo-server-express>
- <https://www.apollographql.com/docs/graphql-subscriptions/setup/>
- <https://www.apollographql.com/docs/graphql-subscriptions/express/>
- <https://www.apollographql.com/docs/apollo-server/data/subscriptions/>
- <https://www.apollographql.com/docs/react/api/core/ApolloClient/#ApolloClient.subscribe>
- <https://github.com/lquixada/cross-fetch>
- <https://github.com/Brooooooklyn/swc-node>

## 프로젝트 생성

```bash
npm init -y
```

## Git과 ESLint를 위한 무시 파일 생성

```bash
touch .gitignore
touch .eslintignore
```

두 파일 모두 다음과 같이 작성한다.

```bash
/node_modules/
```

## Visual Studio Code 세팅

기존 파일을 그대로 다운로드해서 사용한다.

```bash
mkdir .vscode

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/.vscode/settings.json \
    -o .vscode/settings.json
```

## TypeScript 세팅

```bash
npm i -D typescript

npx tsc --init
```

## ESLint 세팅

```bash
npm i -D eslint

npx eslint --init
```

`.eslintrc.js` 파일은 적절히 수정한다.

## Jest와 SWC로 테스트 환경 구축

의존성 설치.

```bash
npm i -D jest @types/jest @swc/jest @swc/core regenerator-runtime
```

Jest 환경 설정 파일 생성.

```bash
touch jest.config.js
```

`jest.config.js` 파일 작성.

```js
module.exports = {
  transform: {
    '^.+\\.(t|j)s$': ['@swc/jest', {
      jsc: {
        parser: {
          syntax: 'typescript',
        },
      },
    }],
  },
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
  ],
};
```

## Apollo Server & Express 설치

```bash
npm i apollo-server-core graphql \
      apollo-server-express express
```

## Subscription 의존성 설치

`PubSub` 의존성 설치.

```bash
npm i graphql-subscriptions
```

`SubscriptionServer` 의존성 설치.

```bash
npm i subscriptions-transport-ws @graphql-tools/schema
```

## 기본 코드 작성

```bash
mkdir src

touch src/index.ts
touch src/app.ts
touch src/server.ts
```

자세한 내용은 코드 참고.

## `swc-node`, `nodemon` 설치 및 실행

```bash
npm i -D swc-node nodemon

npx nodemon --exec swc-node src/index.ts
```

```bash
npm i -D diagnose-endpoint@1.0.12

npx diagnose-endpoint --endpoint=ws://localhost:3000/graphql
```

## Subscription 테스트를 위한 Appolo Client 설치

Apollo Client 설치.

```bash
npm i -D @apollo/client
```

Node.js를 위한 `fetch`, `WebSocket` 구현체 설치.

```bash
npm i -D cross-fetch ws @types/ws
```
