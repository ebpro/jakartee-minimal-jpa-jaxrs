FROM payara/server-full:6.2023.10-jdk17
COPY target/*.war $DEPLOY_DIR
