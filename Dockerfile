# Build stage
FROM openjdk:17-slim AS BUILDER

ENV SRC_HOME=/home/gradle/src

WORKDIR $SRC_HOME

# Copy all files from your project to the Docker image
COPY . .

# Build the project
RUN ./gradlew clean build

# Run stage
FROM openjdk:17-slim

ENV APP_HOME=/home/gradle/src
ENV TZ=Asia/Kolkata

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR $APP_HOME

# Copy the built JAR file from the build stage
COPY --from=BUILDER $SRC_HOME/build/libs/book_exchange-1.0-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","book_exchange-1.0-SNAPSHOT.jar"]