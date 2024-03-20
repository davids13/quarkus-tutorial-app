package com.redhat.developers.reactive;

import io.smallrye.mutiny.Uni;
import jakarta.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v2")
@RegisterRestClient(baseUri = "https://api.punkapi.com")
public interface BeerService {

    @GET
    @Path("/beers")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Beer>> getBeers(@QueryParam("page") int page);

    @GET
    @Path("/beers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<JsonArray> getBeer(@PathParam("id") int id);
}
