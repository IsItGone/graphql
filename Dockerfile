FROM bellsoft/liberica-runtime-container:jre-17.0.13-slim-musl

LABEL authors="oh3823"

WORKDIR /app

COPY build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
