# Change to an available Maven image tag
FROM maven:3.9.5-openjdk-17 AS build  # Use 3.9.5-openjdk-17 instead of 3.9.5-openjdk-21
COPY . .
RUN mvn clean package -DskipTests

# Use the existing OpenJDK image for the runtime
FROM openjdk:21-jdk-slim
COPY --from=build /target/scm2.0-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
