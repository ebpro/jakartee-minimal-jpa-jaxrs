ARG PAYARA_VERSION=6.2023.12-jdk17

## Build, run unit tests and package
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
RUN --mount=type=cache,target=/root/.m2  \
    --mount=type=bind,rw,source=.,target=/app \
    ./mvnw package

## Produces an app image in payara full for development
FROM payara/server-full:${PAYARA_VERSION} AS dev
RUN --mount=type=bind,source=.,target=/app cp /app/target/*.war ${DEPLOY_DIR}/

## Produces an app image in payara micro for production
FROM payara/micro:${PAYARA_VERSION} AS prod
RUN --mount=type=bind,source=.,target=/app cp /app/target/*.war ${DEPLOY_DIR}/
ENTRYPOINT java -jar ${PAYARA_DIR}/payara-micro.jar \
                            --deploy ${DEPLOY_DIR}/*.war