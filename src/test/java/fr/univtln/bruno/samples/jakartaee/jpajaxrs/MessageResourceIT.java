package fr.univtln.bruno.samples.jakartaee.jpajaxrs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@Slf4j
@Testcontainers
class MessageResourceIT {


    private static int APP_SERVER_PORT = 8080;

    //We first start the services with base-docker-compose-dev.yml (without ports mapping and named volumes).
    @Container
    static final ComposeContainer environment =
            new ComposeContainer(new File("docker-compose-ci.yml"))
                    .withEnv("TAG", "test")
                    .withRemoveVolumes(true)
                    .withLogConsumer("app-1",new Slf4jLogConsumer(log))
                    .withExposedService("app-1", APP_SERVER_PORT)
                    .withBuild(true)
                    .waitingFor("app-1",
                            Wait.forHttp("/jakartaee-minimal-jpa-jaxrs/api/v1/application.wadl")
                            .forStatusCode(200));

    //We set up the base query for rest-assured
    @BeforeAll
    static void setUp() {
        //RestAssured.baseURI = "http://" + appServerContainer.getHost() + ":" + appServerContainer.getMappedPort(APP_SERVER_PORT);
        RestAssured.baseURI = "http://" + environment.getServiceHost("app-1", APP_SERVER_PORT) +
                ":" + environment.getServicePort("app-1", APP_SERVER_PORT);
        RestAssured.basePath = "/jakartaee-minimal-jpa-jaxrs/api/v1/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    //We check that find all messages returns the 3 messages.
    @Test
    void findAll() {
        given()
        .when()
                .get("messages")
        .then()
            .assertThat()
                .statusCode(200)
                .body("size()", equalTo(3),
                        "content", hasItems("a dummy message.",
                                                    "another useless message.",
                                                    "Test message from initialisation singleton."));
    }

    //We check that find a message by id returns the correct message.
    @Test
    void find() {
        given()
        .when()
                .get("messages/42b4f0fd-5482-4fac-a63b-1c524b2101b1")
                .then()
        .assertThat()
                .statusCode(200)
                .body(  "id", equalTo("42b4f0fd-5482-4fac-a63b-1c524b2101b1"),
                        "content", equalTo("a dummy message."));
    }
}