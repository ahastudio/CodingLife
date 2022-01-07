# JSON Server 예제

## 기본 설정

```bash
npm init -y

npm i -D json-server

echo '{}' > db.json

npx json-server --watch db.json
```

<http://localhost:3000/>

## 공식 문서의 데이터 가져와서 확인

```bash
echo '{
  "posts": [
    { "id": 1, "title": "json-server", "author": "typicode" }
  ],
  "comments": [
    { "id": 1, "body": "some comment", "postId": 1 }
  ],
  "profile": { "name": "typicode" }
}' > db.json
```

<http://localhost:3000/posts>

<http://localhost:3000/posts/1>

<http://localhost:3000/comments>

<http://localhost:3000/comments/1>

<http://localhost:3000/posts/1/comments>

<http://localhost:3000/posts/1/comments/1>
→ 이건 안 됨. 왜지? 🤔

<http://localhost:3000/posts/1/profile>

## 게시물 추가

[cURL](https://curl.se/) 사용:

```bash
curl -X POST \
  -H 'Content-Type: application/json' \
  -d '{ "author": "me", "title": "제곧내", "body": "." }' \
  http://localhost:3000/posts
```

<http://localhost:3000/posts>

[HTTPie](https://httpie.io/cli) 사용:

```bash
http POST http://localhost:3000/posts author=me title=제곧내 body=.
```
