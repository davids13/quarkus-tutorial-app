package com.redhat.developers.security;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@RequestScoped
@Path("secure")
public class SecureResource {

    @Claim(standard = Claims.preferred_username)
    String username;

    /*
        Add RBAC to SecureResource:
         For this case you need to use a role that is defined in the JWT
         token inside the groups claim (ie Subscriber).

    */

    @RolesAllowed("Not-Subscriber")
    @GET
    @Path("claim")
    public String getClaim() {
        return username;
    }
}
