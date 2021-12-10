# 코딩 문제 풀이

- <https://docs.oracle.com/en/java/javase/17/docs/specs/man/javac.html>
- <https://junit.org/junit5/docs/current/user-guide/>

```bash
curl https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar \
    -o junit-platform-console-standalone-1.8.2.jar
```

```bash
javac --class-path junit-*.jar -d out *.java && \
    java -jar junit-*.jar --class-path out --scan-classpath
```

## 문제

- [Java Substring](https://www.hackerrank.com/challenges/java-substring/problem)
- [합승 택시 요금](https://programmers.co.kr/learn/courses/30/lessons/72413?language=java)
