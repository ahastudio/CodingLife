# Spring 예제

## 작은 Jar 파일 만들기

```bash
./gradlew clean jar

# Jar 파일 용량이 매우 작음.
ls -al app/build/libs/app.jar

# 의존성 문제로 올바르게 실행할 수 없음.
java -jar app/build/libs/app.jar
```

## 의존성을 모두 담은 Jar 파일 만들기

```bash
./gradlew clean fatJar

# Jar 파일 용량이 꽤 큼.
ls -al app/build/libs/app.jar

# 올바르게 실행됨.
java -jar app/build/libs/app.jar
```
