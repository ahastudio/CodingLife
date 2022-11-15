# Deploy Spring Web

## Heroku

`Procfile`:

```procfile
web: java -Dserver.port=$PORT -jar build/libs/bank-0.0.1-SNAPSHOT.jar
```

`system.properties`:

```properties
java.runtime.version=17
```

## Docker

Generate Jar file:

```bash
./gradlew clean bootJar

ls -al build/libs/
```

`Dockerfile`:

```dockerfile
FROM ghcr.io/graalvm/jdk:ol9-java17-22.3.0

WORKDIR /u/myapp

COPY build/libs/*[^plain].jar ./

CMD java -jar *.jar
```

Build Docker image:

```bash
docker build -t bank .
```

Test:

```bash
docker run -it --rm --name bank bank sh
```

Run server:

```bash
docker run -d --name bank \
    -p 8080:8080 \
    bank

docker logs -f bank
```

<http://localhost:8080/>

Terminate server:

```bash
docker stop bank
docker rm bank
```

## Fly

Generate `fly.toml` file:

```bash
fly launch

# App Name: bank-demo
# Select region: nrt (Tokyo, Japan)
# Would you like to set up a Postgresql database now? => N
# Would you like to deploy now? => N
```

Deploy:

```bash
fly deploy
```

Dashboard:

- <https://fly.io/dashboard>
- <https://fly.io/apps/bank-demo>

Open web page:

```bash
fly open
```

<https://bank-demo.fly.dev/>
