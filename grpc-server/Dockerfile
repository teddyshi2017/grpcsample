FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/grpc-server-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]