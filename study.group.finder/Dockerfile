# Use the official maven image to create a build artifact
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app

# Copy the project files into the container
COPY . .

# Package the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:17-slim

# Create a directory for the application
WORKDIR /app

# Copy the packaged jar file into the new image
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
