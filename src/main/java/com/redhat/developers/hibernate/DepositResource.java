package com.redhat.developers.hibernate;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("api/deposits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepositResource {

    DepositRepository depositRepository;

    public DepositResource(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @GET
    public Uni<Response> allDeposits() {
        /*return depositRepository.listAll()
                .map(deposits -> Response.ok().build());*/
        return null;
    }

    /*@POST
    public Uni<Response> createDeposit(Deposit deposit) {
        return deposit.persistAndFlush()
                .map(v -> Response.status(Response.Status.CREATED)
                        .build());
    }

    @GET
    @Path("stream")
    public Multi<Deposit> streamDeposits() {
        return depositRepository.streamAll();
    }*/
}
