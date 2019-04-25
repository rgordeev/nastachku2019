FROM openjdk:alpine

ENV SERVICE_VERSION 0.0.1-SNAPSHOT

RUN mkdir /usr/share/app
RUN mkdir /usr/share/app/logs

VOLUME /usr/share/app/logs

WORKDIR /usr/share/app/

COPY build/libs/serviceb-${SERVICE_VERSION}.jar /usr/share/app/app.jar

# application port
EXPOSE 8080
# debug port
EXPOSE 5005

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} app.jar"]
