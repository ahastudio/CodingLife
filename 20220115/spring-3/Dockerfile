FROM ghcr.io/graalvm/jdk:ol9-java17-22.3.0

WORKDIR /u/myapp

COPY build/libs/*[^plain].jar ./

CMD java -Dserver.port=8080 -Dspring.profiles.active=production -jar *.jar
