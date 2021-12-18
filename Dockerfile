FROM openjdk:11-jre-slim

LABEL MAINTAINER kiranraj

WORKDIR /data

COPY target/test-0.0.1-SNAPSHOT.jar /data/test.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/data/test.jar"]
