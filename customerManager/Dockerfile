# Build stage
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar apenas os arquivos necessários para o build (otimização de cache)
COPY pom.xml .
COPY src ./src

# Build do projeto
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]