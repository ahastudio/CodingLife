# Fastify Demo

```bash
npm i fastify @fastify/cors @fastify/multipart

npm i -D nodemon

npm i -D eslint

npx eslint --init
```

```bash
touch index.js
```

```bash
npx nodemon index.js
```

<http://localhost:3000/>

```bash
curl http://localhost:3000/

curl -X POST --form image='@test.jpg' http://localhost:3000/
```
