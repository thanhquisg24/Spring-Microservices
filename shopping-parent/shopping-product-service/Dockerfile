# Alpine because it's lighter
FROM openjdk:8-jdk-alpine
MAINTAINER Qui Qui <thanhquisg24@gmail.com>

# Set ENV variables
ENV PORT=8081

# Add JAR file and run it as entrypoint
ADD target/shopping-product-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8761