## Build stage ##
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests=true

## Run stage ##
FROM eclipse-temurin:21-alpine

RUN adduser -D hospital

WORKDIR /run
COPY --from=build /app/target/hospital-0.0.1-SNAPSHOT.jar /run/hospital-0.0.1-SNAPSHOT.jar

RUN chown -R hospital:hospital /run

USER hospital

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/run/hospital-0.0.1-SNAPSHOT.jar"]
