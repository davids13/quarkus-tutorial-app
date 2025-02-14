package com.redhat.developers;

import com.redhat.developers.resterrors.InfoNotFoundException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String hello() {
        //return "Hello from RESTEasy Reactive";
        //throw new WithWebApplicationException();
        throw new InfoNotFoundException("Custom exception");
    }
}
