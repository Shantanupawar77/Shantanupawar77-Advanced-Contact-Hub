FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /Shantanupawar77-sch
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /Shantanupawar77-sch
COPY --from=build /Shantanupawar77-sch/target/scm2.0-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
