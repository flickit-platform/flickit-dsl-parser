FROM openjdk:11-jdk-slim
ENV GH_PACKAGES_TOKEN=${GH_PACKAGES_TOKEN}
ENV GH_ACTOR=${GH_ACTOR}
ARG MYFILE
WORKDIR /app
COPY $MYFILE /app/flickit-dsl-parser.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/flickit-dsl-parser.jar"]