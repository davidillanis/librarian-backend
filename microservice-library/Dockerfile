FROM amazoncorretto:17-alpine-jdk

WORKDIR /library

COPY target/microservice-library-0.0.1-SNAPSHOT.jar library.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "library.jar"]
