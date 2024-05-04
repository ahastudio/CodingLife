FROM gradle:8.7-jdk21-alpine AS builder

WORKDIR /u/app

COPY gradle ./gradle
COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .

RUN ./gradlew getDeps --no-daemon --info

COPY . .

RUN ./gradlew shadowJar --no-daemon --info

###############################################################################

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /u/app

COPY --from=builder /u/app/build/libs/app-all.jar app.jar

CMD java -jar app.jar
