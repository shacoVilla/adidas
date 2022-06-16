FROM openjdk:8-jdk-alpine
MAINTAINER ADIDAS
VOLUME /tmp
ADD target/email-service-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8087
