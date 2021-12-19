# Java Gradle Project Demo

- <https://docs.gradle.org/current/userguide/java_plugin.html>
- <https://junit.org/junit5/>
- <https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter/5.8.2>
- <https://github.com/junit-team/junit5-samples/tree/main/junit5-jupiter-starter-gradle>

```bash
touch settings.gradle
echo "rootProject.name = 'demo'" > settings.gradle

touch build.gradle

gradle wrapper

touch build.gradle

mkdir -p src/main/java
mkdir -p src/main/resources
mkdir -p src/test/java
mkdir -p src/test/resources

touch src/main/java/HelloWorld.java

touch src/test/java/SimpleTest.java
```

```bash
./gradlew tasks

./gradlew tasks --all
```

```bash
./gradlew build --info
```

```bash
./gradlew compileJava

java -cp build/classes/java/main HelloWorld
```

```bash
./gradlew test

./gradlew test --info
```

```bash
open build/reports/tests/test/index.html
```
