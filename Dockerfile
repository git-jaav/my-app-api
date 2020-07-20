FROM openjdk:8-jdk-alpine
LABEL maintainer="araucovillar@gmail.com"
ARG BUILD_PROFILE
VOLUME /tmp
WORKDIR /workspace
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
#ENV PAR_VAL_CORREO_HOST="smtp.gmail.com"
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Dspring.profiles.active=${BUILD_PROFILE}  /workspace/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${BUILD_PROFILE}","-jar","/workspace/app.jar"]
EXPOSE 8088