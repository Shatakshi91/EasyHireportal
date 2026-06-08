# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml first to download dependencies and cache them
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the package
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application with a lightweight JRE image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Configure environment variables
ENV PORT=8080

# Run the app with JVM tuning for the 512MB memory limit on Render's free tier
# -XX:MaxRAMPercentage=75.0 ensures the JVM uses at most 75% (~384MB) of the 512MB container memory
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-Dserver.port=${PORT}", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
