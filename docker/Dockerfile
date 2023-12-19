ARG PAYARA_VERSION=6.2023.12-jdk17

FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 ./mvnw -Dskiptest package

FROM payara/server-full:${PAYARA_VERSION} AS dev
COPY --from=build /app/target/*.war ${DEPLOY_DIR}/

FROM payara/micro:${PAYARA_VERSION} AS prod
COPY --from=build /app/target/*war ${DEPLOY_DIR}/
ENTRYPOINT java -jar ${PAYARA_DIR}/payara-micro.jar \
                            --deploy ${DEPLOY_DIR}/*.war