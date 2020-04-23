FROM ragnax/mvn-build as build
WORKDIR /workspace/app

RUN mkdir key
RUN cp /workspace/config/keys/cloud_sql.json  key/cloudsql.json

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN cp /workspace/config/properties/bootstrap/ragnax-oauthusuario-dev.properties src/main/resources/bootstrap.properties
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG APP_NAME=ragnax-oauthusuario
ARG ENV=dev
ARG VERSION=1.0.0-SNAPSHOT
ARG DEPENDENCY=/workspace/app
ARG ARTIFACT_NAME=ragnax-oauthusuario-1.0.0-SNAPSHOT.jar

COPY --from=build ${DEPENDENCY}/key /key
COPY --from=build ${DEPENDENCY}/target/${ARTIFACT_NAME} /app/ragnax-oauthusuario.jar

ENTRYPOINT ["java","-jar","/app/ragnax-oauthusuario.jar"]