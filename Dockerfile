FROM openjdk:11-jdk-slim

ENV GH_PACKAGES_TOKEN=${GH_PACKAGES_TOKEN}
ENV GH_ACTOR=${GH_ACTOR}
ARG MYFILE

WORKDIR /app
RUN ls
COPY $MYFILE /app/myapp.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/myapp.jar"]