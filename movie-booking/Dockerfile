FROM openjdk:22-slim AS build

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:22-slim

WORKDIR /app

COPY --from=build /app/target/*.jar api.jar 

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api.jar"]