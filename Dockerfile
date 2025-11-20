# ------------ Stage 1: Build with Maven ------------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy source code
COPY pom.xml .
COPY src ./src

# Build the project (creates jar file)
RUN mvn clean package -DskipTests


# ------------ Stage 2: Run the JAR ------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy JAR file from build step
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Start command
ENTRYPOINT ["java", "-jar", "app.jar"]
