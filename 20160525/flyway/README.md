# Flyway 예제

Docker로 MariaDB 서버 띄우기:
```
$ export MYSQL_ROOT_PASSWORD=password
$ export MYSQL_DATABASE=test
$ docker run -d --name=mariadb -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  mariadb \
  --character-set-server=utf8 \
  --collation-server=utf8_general_ci
```

마이그레이션 실행:
```
$ ./gradlew flywayMigrate
```
