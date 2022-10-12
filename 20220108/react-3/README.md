# React 프로그램 예제

Mock API 서버 실행:

```bash
npx json-server --watch db.json
```

웹 서버 실행:

```bash
npm start
```

## Fly로 배포

```bash
REACT_APP_API_BASE_URL=https://board-spring.fly.dev npm run build
```

```bash
fly launch

fly deploy
```
