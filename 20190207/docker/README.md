# Docker & Golang Example

## Dive into the Docker container

```bash
docker run -it --rm --name test \
    -p 8080:8080 \
    -v $(PWD):/go/src/github.com/ahastudio/example \
    golang:1.11.5 bash
```

## Inside the Docker container

```bash
cd /go/src/github.com/ahastudio/example

go test ./...

go run main.go
```

- <http://localhost:8080/>
- <http://localhost:8080/add>
- <http://localhost:8080/add?x=1&y=2&z=3>
- <http://localhost:8080/404>

```bash
godoc -http=:8080
```

<http://localhost:8080/pkg/>

## Dockerize

```bash
# Build an image

docker build -t test-image .

# Run

docker run -d --name test -p 8080:8080 test-image

docker container ls

docker logs -f test

# Execute

docker exec -it test bash

docker exec -it test ls -al

docker exec -it test cat hello.txt

# Stop

docker stop test

docker container ls

docker container ls -a

docker rm test

# Run with another command

docker run -it --rm --name test -p 8080:8080 \
    test-image ls -al

docker run -it --rm --name test -p 8080:8080 \
    test-image go run main.go

docker run -it --rm --name test -p 8080:8080 \
    test-image go test ./...

docker run -it --rm --name test -p 8080:8080 \
    test-image godoc -http=:8080
```
