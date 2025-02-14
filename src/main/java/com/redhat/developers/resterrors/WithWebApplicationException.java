package com.redhat.developers.resterrors;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class WithWebApplicationException extends WebApplicationException {

    public WithWebApplicationException() {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ClientErrors(401, "Web"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
