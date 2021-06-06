# FROM openjdk:8-jre-alpine
# VOLUME /tmp
# ARG JAR_FILE=build/libs/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


FROM maven:3.8.1-openjdk-11
ADD . .
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine
WORKDIR /src
COPY --from=0 target/*.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/src/app.jar"]
EXPOSE 8888