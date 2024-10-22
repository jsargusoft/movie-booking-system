FROM openjdk:21-jdk-slim


WORKDIR /movie-booking

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven && apt-get clean && \
    mvn package -DskipTests


COPY /target/movie-booking-0.0.1-SNAPSHOT.jar /app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]


# movie-booking-0.0.1-SNAPSHOT.jar