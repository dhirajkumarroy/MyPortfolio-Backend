# Stage 1: Build the application using Maven
# Use a base image that includes Maven and JDK
FROM maven:3.8.5-openjdk-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY . .

# Run the Maven build to create the executable JAR
RUN mvn clean install -DskipTests

# Stage 2: Create the final, smaller image
# Use a lightweight JRE image for the final application
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR from the 'build' stage to the final image
COPY --from=build /app/target/My-Portfolio-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
