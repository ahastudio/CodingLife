# Go HTTP 서버 데모

```bash
docker pull golang:1.18.3-alpine

docker run -it --rm --name golang \
    -p 3000:3000 \
    -v $(pwd):/go/src/demo \
    golang:1.18.3-alpine sh

cd /go/src/demo

go mod init demo

touch server.go

go fmt .

go run .
```
