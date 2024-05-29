FROM eclipse-temurin as build
# Set the working directory inside the container
WORKDIR /app
# Copy the entire project into the container
COPY . .
# Build the application
RUN ./gradlew clean build

# Second stage: Create the final image
FROM openjdk:23-ea-23-jdk-oraclelinux9

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/koala-system-0.0.1-SNAPSHOT.jar koala-system-0.0.1-SNAPSHOT.jar

# Expose the port the application runs on
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "koala-system-0.0.1-SNAPSHOT.jar"]