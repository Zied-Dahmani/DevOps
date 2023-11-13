# Use the official OpenJDK 8 image as the base image
FROM openjdk:11

# Expose the port your application will run on
EXPOSE 8082

# Set environment variables
ENV NEXUS_USERNAME=admin
ENV NEXUS_PASSWORD=nexuss12
ENV NEXUS_REPO_URL=http://192.168.33.10:8086/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar

# Download the JAR file from Nexus and copy it to the container
RUN curl -L -o devops.jar -u $NEXUS_USERNAME:$NEXUS_PASSWORD $NEXUS_REPO_URL

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "devops.jar"]
