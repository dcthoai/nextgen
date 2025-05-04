# ==============================
# STAGE 1: Build with Maven
# ==============================
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copy only Maven config files first to leverage Docker cache
COPY pom.xml ./

# Cache Maven dependencies
RUN mvn dependency:go-offline -B

# Install Node.js v18.20.5 and npm 10.8.2
RUN mvn frontend:install-node-and-npm

# Copy package.json and package-lock.json for caching node_modules
COPY package.json package-lock.json ./
RUN mvn frontend:npm

# Copy Angular config files
COPY angular.json tsconfig*.json ./

# Copy full source code
COPY src ./src

# Build backend and frontend (Angular)
RUN mvn clean package -Pprod -DskipTests

# ==============================
# STAGE 2: Run JAR
# ==============================
FROM openjdk:17-jdk-slim

WORKDIR /app

# Install Nginx
RUN apt-get update && apt-get install -y nginx

# Create nginx user and group
RUN groupadd -r nginx && useradd -r -g nginx nginx

# Copy Spring Boot JAR from the build stage
COPY --from=build /app/target/nextgen-*.jar app.jar

# Copy the built Angular app into Nginx's default directory
COPY --from=build /app/target/classes/static /usr/share/nginx/html

# Copy configure Nginx file
COPY nginx.conf /etc/nginx/nginx.conf

# Expose necessary ports (8080 for Spring Boot, 80 for Nginx serving Angular)
EXPOSE 8080
EXPOSE 80

# Start both Spring Boot app and Nginx in the same container
CMD service nginx start && java -jar app.jar
