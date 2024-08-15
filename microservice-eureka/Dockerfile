FROM amazoncorretto:17-alpine-jdk

WORKDIR /eureka

COPY target/microservice-eureka-0.0.1-SNAPSHOT.jar eureka.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka.jar"]
