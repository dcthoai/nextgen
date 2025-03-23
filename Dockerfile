# Using Maven with OpenJDK 17 to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory in container
WORKDIR /app

# Copy pom.xml file to download dependencies first (take advantage of Docker cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code and build it into a WAR file
COPY src ./src
RUN mvn package -Pprod -DskipTests

# Switch to official Tomcat image
FROM tomcat:10.1.39-jdk17
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR file to Tomcat's webapps directory
COPY --from=build /app/target/*.war ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat (by default Tomcat entrypoint will run Catalina)
CMD ["catalina.sh", "run"]
