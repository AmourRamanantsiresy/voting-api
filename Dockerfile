FROM maven:3.8.5-openjdk AS build
COPY . .
RUN mvn clean package

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/voting-app-0.0.1-SNAPSHOT.jar voting-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","voting-app.jar"]