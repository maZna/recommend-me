#Build Stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#Package Stage
FROM java:8-jdk-alpine
EXPOSE 8090:8080
COPY --from=build /home/app/target/recommend-me-backend-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch recommend-me-backend-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","recommend-me-backend-0.0.1-SNAPSHOT.jar"]
