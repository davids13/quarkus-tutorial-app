package com.redhat.developers.healthcheck;

import io.smallrye.health.checks.UrlHealthCheck;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.HttpMethod;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
public class CustomHealthCheck {

    //curl localhost:8080/q/health | jq

    @ConfigProperty(name = "com.redhat.developers.FruityViceService/mp-rest/url")
    String externalURL;

    @Readiness
    HealthCheck checkURL() {
        return new UrlHealthCheck(externalURL + "/api/fruit/banana")
                .name("ExternalURL health check").requestMethod(HttpMethod.GET).statusCode(200);
    }
}
