# MODEL IMAGE
FROM eclipse-temurin:21.0.3_9-jdk

# CONTAINER PORT
EXPOSE 8080

# CONTAINER ROOT DIRECTORY
WORKDIR /root

# COPY AND PAST INSIDE CONTAINER
COPY ./pom.xml /root
# ADD MAVEN
COPY ./.mvn /root/.mvn
# MAVEN EXEC
COPY ./mvnw /root

# DOWNLOAD DEPENDENCY
RUN ./mvnw dependency:go-offline

# COPY SRC
COPY ./src /root/src

# BUILD APPLICATION
RUN ./mvnw clean install

# lift application
ENTRYPOINT [ "java", "-jar", "/root/target/Reservations-0.0.1-SNAPSHOT.jar" ]
