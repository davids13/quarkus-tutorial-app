package com.redhat.developers.rateLimit;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Path("greeting")
public class GreeterResource {

    // https://developers.redhat.com/articles/2022/03/29/develop-basic-rate-limiter-quarkus-and-redis#

    private final static Logger LOGGER = Logger.getLogger(GreeterResource.class.getName());

    @Inject
    RedisClient redisClient;

    @ConfigProperty(name = "requests.per.minute", defaultValue = "15")
    int customLimit;

    @GET
    @Path("{message}")
    @Produces(MediaType.TEXT_PLAIN)
    public jakarta.ws.rs.core.Response hello(@PathParam("message") String message) {
        final LocalDateTime now = LocalDateTime.now();
        String key = message + now.getSecond();
        Response requests = redisClient.get(key);
        int requestNo = (requests != null) ? requests.toInteger() : 0;

        if (requestNo >= customLimit) {
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.TOO_MANY_REQUESTS)
                    .header("X-Rate-Limit-Retry-After-Seconds", 60 - now.getSecond())
                    .entity(false)
                    .build();
        }

        LOGGER.info("Hello from GreeterResource");
        return jakarta.ws.rs.core.Response.status(200).entity("Hello from GreeterResource").build();
    }
}
