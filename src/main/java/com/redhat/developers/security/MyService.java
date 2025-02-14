package com.redhat.developers.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("auth")
@RequestScoped
public interface MyService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String hello();
}
