FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/avaliai-0.0.1-SNAPSHOT.jar .

#CMD ["java", "-jar", "avaliai-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/avaliai-0.0.1-SNAPSHOT.jar"]