# HTTP

Node.js 기본 모듈의 타입 정의가 필요함.

```bash
npm i -D @types/node
```

```bash
npx ts-node http-client.ts
```

타입 추론을 위해 `tsconfig.json` 파일 생성.

```bash
npx tsc --init
```

```bash
npx ts-node http-server.ts
```

트랜스파일 결과를 별도의 파일로 만들지 않음.

```bash
npx esbuild tickets.tsx
```
