# Stage 1: Build (Maven + Java 21)
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and fetch dependencies
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -B dependency:go-offline

# Copy source code and build jar
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -B package -DskipTests

# Stage 2: Runtime (Java 21 JRE)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/zayka-backend-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]