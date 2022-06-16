FROM openjdk:8-jdk-alpine
MAINTAINER ADIDAS
VOLUME /tmp
ADD target/subscription-service-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]
EXPOSE 8088

