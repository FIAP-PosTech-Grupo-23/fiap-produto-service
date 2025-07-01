FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-alpine
COPY --from=build /usr/src/app/target/fiap-produto-service-0.0.1-SNAPSHOT.jar /app/fiap-produto-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
CMD ["java","-jar","/app/fiap-produto-service-0.0.1-SNAPSHOT.jar"]