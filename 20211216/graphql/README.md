# Apollo Server 예제

- <https://www.typescriptlang.org/>
- <https://typescript-eslint.io/>
- <https://www.apollographql.com/docs/apollo-server/getting-started/>
- <https://www.apollographql.com/docs/apollo-server/api/apollo-server/>
- <https://www.apollographql.com/docs/apollo-server/schema/schema/>
- <https://www.apollographql.com/docs/apollo-server/data/resolvers/>
- <https://www.apollographql.com/blog/graphql/basics/mutation-vs-query-when-to-use-graphql-mutation/>
- <https://studio.apollographql.com/sandbox/explorer>

```bash
npm init -y

npm i apollo-server graphql

npm i -D typescript

npx tsc --init

npm i -D jest @types/jest ts-jest

npm i -D eslint

npx eslint --init
```

```bash
touch .gitignore
touch .eslintignore
```

```bash
mkdir -p src
touch src/index.ts
touch src/server.ts
```

```bash
npm i -D ts-node nodemon

npx nodemon src/index.ts
```

```graphql
query ExampleQuery {
  books {
    title
    author
  }
}
```

```graphql
mutation ExampleMutation($title: String, $author: String) {
  addBook(title: $title, author: $author) {
    title
    author
  }
}
```
