FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ARG BATCH_PARAM_DATE
RUN echo "env ${BATCH_PARAM_DATE}"
#ENTRYPOINT ["java", "-jar", "/app.jar", "date=$BATCH_PARAM_DATE --job.name=$BATCH_JOB_NAME --version=$BATCH_PARAM_VERSION"]
ENTRYPOINT ["java", "-jar", "/app.jar", "date=${BATCH_PARAM_DATE}", "--job.name=statisticInitBatchJob"]