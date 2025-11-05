# Step 1: Use an official JDK image
FROM eclipse-temurin:17-jdk

# Step 2: Create app directory
WORKDIR /app

# Step 3: Copy JAR file from root
COPY FraudAlert-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port 8080 (default for Spring Boot)
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
