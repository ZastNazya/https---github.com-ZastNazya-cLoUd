FROM openjdk:17-jdk-alpine
MAINTAINER IoT Student
COPY Lab1.jar java-app-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/java-app-docker.jar"]
