package com.redhat.developers.auth;

import io.quarkus.rest.client.reactive.ClientBasicAuth;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("api")
@RegisterRestClient
@ClientBasicAuth(username = "the_user", password = "the_pass")
public class MyServiceClient {

    // https://www.javacodegeeks.com/applying-clientbasicauth-in-quarkus.html

    @GET
    @Path("data")
    @Produces(MediaType.APPLICATION_JSON)
    public String getData() {
        return "Hello";
    }
}
