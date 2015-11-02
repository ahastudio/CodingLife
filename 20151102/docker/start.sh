docker build -t test .
docker run -it -p 80:8000 -v `pwd`/work:/work test
