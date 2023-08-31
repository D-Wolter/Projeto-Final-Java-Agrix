# JDK imagem build temporaria para compilação
FROM maven:3-openjdk-17 as build-image
WORKDIR /to-build-app
COPY . .
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package

# JRE imagem para execução da aplicação
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build-image /app/target/*.jar /app/app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]