# Step 1: Build stage
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

# Copy Maven wrapper and project files
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (helps with caching)
RUN ./mvnw dependency:go-offline

# Copy the entire source code
COPY src src

# Package the application without running tests
RUN ./mvnw clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port (Render detects this)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]