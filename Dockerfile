# Stage 1: Build Stage
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app

# Copy the Maven project files
COPY . .

# Build the project and package the JAR
RUN mvn clean package -DskipTests

# Stage 2: Runtime Stage
FROM openjdk:17.0.1-jdk-slim-buster
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/donHub-0.0.1-SNAPSHOT.jar donHub.jar

# Expose the port
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "donHub.jar"]
