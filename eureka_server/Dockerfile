FROM amazoncorretto:17
COPY /build/libs/eureka_server-1.0-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]
