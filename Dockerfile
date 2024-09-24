# Use an available Maven image (OpenJDK 17) for the build stage
FROM maven:3.9.5-openjdk-17 AS build  
WORKDIR /app              # Set the working directory
COPY . .                  # Copy all files to the working directory
RUN mvn clean package -DskipTests  # Build the application

# Use OpenJDK 21 for the runtime environment
FROM openjdk:21-jdk-slim 
WORKDIR /app              # Set the working directory for the runtime
COPY --from=build /app/target/scm2.0-0.0.1-SNAPSHOT.jar demo.jar  # Copy the JAR file
EXPOSE 8080               # Expose port 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]  # Command to run the application

