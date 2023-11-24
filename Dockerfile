FROM openjdk:17
COPY /target/weatherApi-0.0.1-SNAPSHOT.jar weather-app.jar
ENTRYPOINT ["java","-jar","/weather-app.jar"]