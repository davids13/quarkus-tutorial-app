package com.redhat.developers.boundary;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@ApplicationScoped
@Path("api/bank")
public class BankResource {

    @ConfigProperty(name = "bank.name", defaultValue = "Bank of Default")
    String greeting;

    // With Optional types, MicroProfile Config will not throw an exception if a property is not defined.
    @ConfigProperty(name = "app.mobileBanking")
    Optional<Boolean> mobileBanking;

    @GET
    @Path("name")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean getName() {
        return mobileBanking.orElse(false); // If the mobile Banking field is undefined, returns false
    }
}
