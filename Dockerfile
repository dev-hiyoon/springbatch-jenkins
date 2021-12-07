FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "--job.name=${BATCH_JOB_NAME} --version=${BATCH_PARAM_VERSION} date=${BATCH_PARAM_DATE}", "-jar", "/app.jar"]