# Use a Java base image with a lightweight JRE (Java Runtime Environment).
# The Temurin 21-jre-jammy image is a great choice for this purpose.
FROM eclipse-temurin:21-jre-jammy

# Set the working directory inside the container to '/app'.
# All subsequent commands will be run relative to this directory.
WORKDIR /app

# Copy the built JAR file from your local 'target' directory into the container.
# The JAR file is renamed to 'app.jar' for simplicity.
# This assumes you have already run 'mvn clean install' locally.
COPY target/My-Portfolio-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application runs on.
# This tells the container host (Render) that the app listens on this port.
EXPOSE 8080

# This is the command that will be executed when the container starts.
# It runs the Java application using the 'java -jar' command.
ENTRYPOINT ["java", "-jar", "app.jar"]
