FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/crypto-market-event-data-service.jar app.jar
ENV JAVA_OPTS="-XX:+PrintFlagsFinal"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]