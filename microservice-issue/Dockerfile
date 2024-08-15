FROM amazoncorretto:17-alpine-jdk

WORKDIR /issue

COPY target/microservice-issue-0.0.1-SNAPSHOT.jar issue.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "issue.jar"]
