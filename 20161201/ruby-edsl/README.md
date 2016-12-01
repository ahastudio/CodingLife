EDSL(Embedded Domain Specific Language)
[https://github.com/ahastudio/til/blob/master/ruby/20161201-edsl.md](http://j.mp/2gbVefz)

[HTTPie](https://httpie.org/)를 이용해 테스트:
```
$ http GET http://localhost:8080/
$ http GET http://localhost:8080/say
$ http -f POST http://localhost:8080/say message=Hi
$ http GET http://localhost:8080/say
```
