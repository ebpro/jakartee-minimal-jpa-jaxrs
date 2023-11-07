# A simple JakartaEE application 

## Launch

```
./mvnw clean package cargo:run
```
The [Maven Wrapper](https://maven.apache.org/wrapper/) is already included in the project, so a Maven install is not actually needed. 
You may first need to execute `chmod +x mvnw`.

Once the runtime starts, you can access the project at http://localhost:8080/jakartaee-minimal-jpa-jaxrs/.

You can also run the project via [Docker](https://docs.docker.com/get-docker/). 

```
./mvnw clean package
docker build -t jakartaee-minimal-jpa-jaxrs:0.1.0 .
```

You can then run the Docker image by executing:

```
docker run -it --rm -p 8080:8080 jakartaee-minimal-jpa-jaxrs:0.1.0
```

Once the runtime starts, you can access the project at http://localhost:8080/jakartaee-hello-world.

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

