# Java Sample

## SDKMAN! 세팅

- <https://sdkman.io/usage>
- <https://github.com/ahastudio/til/blob/main/java/sdkman.md>

```bash
sdk selfupdate

sdk env install

sdk current

sdk env
```

## 프로젝트 생성

```bash
gradle init
```

여기선 `app` 디렉터리를 사용하지 않도록 구조 조정함.

## 의존성 확인

```bash
./gradlew dependencies
```

## 의존성 다운로드 테스트

- <https://docs.gradle.org/current/userguide/more_about_tasks.html>
- <https://docs.gradle.org/current/userguide/working_with_files.html>
- <https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Copy.html>

`build.gradle.kts` 파일에 `getDeps` 태스크 추가.

```kt
tasks.register<Copy>("getDeps") {
    from(configurations.runtimeClasspath)
    from(configurations.compileClasspath)
    into("tmp/libs/")
}
```

```bash
./gradlew getDeps
```

## 빌드

```bash
./gradlew assemble
```

## Fat Jar 빌드

- <https://plugins.gradle.org/plugin/io.github.goooler.shadow>

`build.gradle.kts` 파일에 플러그인 추가.

```kt
plugins {
    // ...(중략)...

    id("io.github.goooler.shadow") version "8.1.7"
}
```

```bash
./gradlew shadowJar
```

## Gradle Docker 이미지 확인

<https://hub.docker.com/_/gradle/>

```bash
docker run -it --rm gradle:8.7-jdk21-alpine sh
```

## Docker 이미지 빌드

```bash
docker build --progress=plain --platform linux/amd64 -t java-sample .
```

## Docker 컨테이너 실행

```bash
docker run -it --rm --platform linux/amd64 java-sample
```
