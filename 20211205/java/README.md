# Hello, world

## 현재 폴더에 `class` 파일 만들어서 실행하기

컴파일

```bash
javac Hello.java
```

파일 확인

```bash
ls -al
```

실행

```bash
java Hello
```

## `classes` 폴더에 `class` 파일 만들어서 실행하기

기존 `class` 파일 삭제

```bash
rm Hello.class
```

컴파일

```bash
javac -d classes Hello.java
```

실행

```bash
java --class-path=classes Hello

# 또는

java -classpath classes Hello
```

컴파일 & 실행

```bash
javac -d classes Hello.java && java --class-path=classes Hello
```

## 숫자 야구 게임

```bash
javac -d classes Baseball.java && java --class-path=classes Baseball
```
