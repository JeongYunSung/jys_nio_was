FROM openjdk:11-jre-slim
ARG JAR_FILE=build/libs/jas.jar
COPY run.sh .
COPY ${JAR_FILE} jas.jar
ENTRYPOINT ["bash", "run.sh"]