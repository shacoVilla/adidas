FROM openjdk:8-jdk-alpine
MAINTAINER ADIDAS
VOLUME /tmp
ADD target/eureka-server-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8761
