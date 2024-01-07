#1st Stage
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY pom.xml /app/
RUN mvn dependency:go-offline -B
COPY src /app/src
RUN mvn package -DskipTests

#2nd Stage
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/aziz-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "aziz-0.0.1-SNAPSHOT.jar"]