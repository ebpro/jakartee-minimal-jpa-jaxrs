#!/bin/bash
docker run \
	--rm -it \
	-v $PWD:$PWD \
	-w $PWD \
	-v ~/.m2:/root/.m2 \
	-v /var/run/docker.sock:/var/run/docker.sock \
	-e TESTCONTAINERS_HOST_OVERRIDE=host.docker.internal \
	eclipse-temurin:17-jdk \
	./mvnw $@
