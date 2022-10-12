FROM eclipse-temurin:17

WORKDIR /u/myapp

COPY build/libs/*[^plain].jar ./

CMD java -Dserver.port=8080 -Dspring.profiles.active=production -jar *.jar
