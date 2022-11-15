##use an openJDK Runtime as a parent image
FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java","-jar","/app.jar"]
