FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/DevOps_Project-2.1.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
