FROM openjdk:8
COPY target/restexample.jar /app/

#run the spring boot application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/restexample.jar"]