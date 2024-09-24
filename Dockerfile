# Use Maven to build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /Shantanupawar77-sch  # Set the working directory
COPY . .       # Copy the entire project to the working directory
RUN mvn clean package -DskipTests  # Build the application and skip tests

# Use OpenJDK for the runtime image
FROM openjdk:17.0.1-jdk-slim
WORKDIR /Shantanupawar77-sch  # Set the working directory
COPY --from=build /Shantanupawar77-sch/target/scm2.0-0.0.1-SNAPSHOT.jar demo.jar  # Copy the JAR file from the build stage
EXPOSE 8080  # Expose the application port
ENTRYPOINT ["java", "-jar", "demo.jar"]  # Set the command to run the application
