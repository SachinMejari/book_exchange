# Use OpenJDK as the base image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY build/libs/*.jar app.jar

# Expose port 8080 (or any other port your application uses)
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]