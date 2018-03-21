# See https://hub.docker.com/_/java/
FROM java:8
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

# Set the working directory
WORKDIR /
