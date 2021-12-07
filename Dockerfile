FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENV BATCH_PARAM_DATE 20211201
ENTRYPOINT ["java", "-jar", "/app.jar"]