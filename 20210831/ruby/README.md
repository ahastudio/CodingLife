# Sinatra web application in Docker container

## Docker 컨테이너 실행

```bash
docker run -it --rm \
    -w /work \
    -v $(pwd):/work \
    -v $(pwd)/.bundle:/usr/local/bundle \
    -p 3000:4567 \
    ruby:3.4.2 bash
```

## 의존성 설치

```bash
gem update --system
gem install bundler --no-document
bundle install
```

## 서버 실행

```bash
PORT=3000 rerun app.rb
```

## 컨테이너 이미지 빌드

```bash
docker build -t sinatra-demo .
```

## 컨테이너 실행

```bash
docker run -it --rm --name demo \
    -p 3000:4567 \
    sinatra-demo
```
