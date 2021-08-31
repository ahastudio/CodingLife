# Sinatra web application in Docker container

## Docker 컨테이너 실행

```bash
docker run -it --rm \
    -w /work \
    -v $(pwd):/work \
    -v $(pwd)/.bundle:/usr/local/bundle \
    -p 4567:4567 \
    ruby:3.0.2 bash
```

## 의존성 설치

```bash
gem update --system
gem install bundler webrick sinatra --no-document
```

## 서버 실행

```bash
ruby app.rb
```
