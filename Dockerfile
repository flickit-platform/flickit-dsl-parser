FROM openjdk:11-jdk-slim

ENV GH_PACKAGES_TOKEN=${GH_PACKAGES_TOKEN}
ENV GH_ACTOR=${GH_ACTOR}
ARG MYFILE
RUN mkdir -p /opt/dsl-parser/upload
RUN echo > /opt/dsl-parser/sequence-code.txt
WORKDIR /app
COPY $MYFILE /app/myapp.jar
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar", "--spring.profiles.active=prod"]