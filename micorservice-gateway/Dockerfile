FROM amazoncorretto:17-alpine-jdk

WORKDIR /gateway

COPY target/micorservice-gateway-0.0.1-SNAPSHOT.jar gateway.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "gateway.jar"]
