FROM openjdk:11-jdk-slim

ARG JAR_FILE=target/*.jar

RUN mkdir /dropthebeatbox

COPY ${JAR_FILE} /dropthebeatbox/server.jar

ENTRYPOINT ["java", "-jar", "/dropthebeatbox/server.jar"]