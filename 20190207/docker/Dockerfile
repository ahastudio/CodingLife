FROM golang:1.11.5

ENV APP_PATH=/go/src/github.com/ahastudio/example

WORKDIR $APP_PATH

ADD . $APP_PATH

RUN echo 'hello' > hello.txt

CMD go test ./... && \
    go run main.go
