# Base image with Maven + JDK 11
FROM maven:3.9.6-eclipse-temurin-11 AS builder

# Set working directory inside container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Run Maven tests (Karate runs during test phase)
RUN mvn clean test

# Create a folder to hold test reports
RUN mkdir -p /app/karate-report

# Copy report files (HTML, surefire, extent, etc.) to report folder
RUN cp -r target/*.html target/surefire-reports /app/karate-report/
