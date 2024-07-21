FROM openjdk:17-jdk-slim

LABEL authors="oh3823"

WORKDIR /app

COPY build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
