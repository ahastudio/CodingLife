# Flyway 예제

Docker로 MariaDB 서버 띄우기:
```
$ export MYSQL_ROOT_PASSWORD=password
$ docker run -d --name=mariadb -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  mariadb
```

데이터베이스 생성:
```
$ export DATABASE_NAME=test
$ docker exec mariadb bash -c \
  "echo 'CREATE DATABASE $DATABASE_NAME \
    CHARACTER SET utf8 COLLATE utf8_general_ci;' \
    | mysql -p$MYSQL_ROOT_PASSWORD"
```

마이그레이션 실행:
```
$ ./gradlew flywayMigrate
```
