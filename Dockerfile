FROM ubuntu:latest
LABEL authors="sonjiwoo"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]