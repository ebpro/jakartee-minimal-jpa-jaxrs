# A simple JakartaEE application 

## Launch

Build and launch with [Docker Compose](https://docs.docker.com/compose/): payara micro + postgresql. See .env file for configuration.

```
docker compose up --build --daemon
```

Once the runtime is started, you can access the project at http://localhost:8080/jakartaee-hello-world.

## Usage

```bash
curl -X GET \
  localhost:8080/jakartaee-minimal-jpa-jaxrs/api/messages
```

```bash
 curl -X POST  \
  -H "Content-Type: application/json"  \
  -d '{"Message":{"content":"Testjij"}}' \
  localhost:8080/jakartaee-minimal-jpa-jaxrs/api/messages
```

## Development

Build and launch with [Docker Compose](https://docs.docker.com/compose/): payara full + postgresql.
See .env file for configuration.

```
STAGE=dev docker compose up --build --daemon
```

Compile and redeploy with [Maven](https://maven.apache.org/):

```
./mvnw verify
```

The [Maven Wrapper](https://maven.apache.org/wrapper/) is already included in the project,
so Maven install is not needed. 
You may first need to execute `chmod +x mvnw`.

Integration tests are orchestrated with [testcontainers docker compose module](https://java.testcontainers.org/modules/docker_compose/).

Rest services are tested with [Rest Assured](https://rest-assured.io/).




