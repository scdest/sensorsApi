FROM maven:3.8.6-amazoncorretto-17 AS maven
LABEL MAINTAINER="scdest9200@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM amazoncorretto:17-alpine-jdk

ARG JAR_FILE=sensor-api-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","sensor-api-0.0.1-SNAPSHOT.jar"]