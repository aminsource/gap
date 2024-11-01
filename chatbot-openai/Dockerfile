# Use Eclipse Temurin JDK 23 as base image
FROM eclipse-temurin:23

# Set working directory
WORKDIR /app

# Copy the JAR file to the container
ARG JAR_FILE=build/libs/chatbot-openai-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} chatbot.jar

# Expose port 8080 for the service
EXPOSE 8090

# Start the application
ENTRYPOINT ["java", "-jar", "/app/chatbot.jar"]
