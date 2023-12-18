package fr.univtln.bruno.samples.jakartaee.jpajaxrs;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api/v1")
@DataSourceDefinition(
        name="java:global/MyAppDS",
        className="${ENV=DB_DRIVERCLASSNAME:org.postgresql.ds.PGSimpleDataSource}",
        user = "${ENV=DB_USERNAME:dba}",
        password = "${ENV=DB_PASSWORD:secretsecret}",
        url = "${ENV=DB_JDBC_URL:jdbc:postgresql://localhost:5432/testdb}"
)
public class ApplicationConfig extends Application {
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.jsonFeature", "JacksonFeature");
        return props;
    }
}
