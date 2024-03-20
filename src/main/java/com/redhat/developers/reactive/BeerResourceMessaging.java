package com.redhat.developers.reactive;

import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/beer")
public class BeerResourceMessaging {

    // curl localhost:8080/beer

    /*
        Quarkus has SmallRye Reactive Messaging which is an implementation of the Eclipse MicroProfile Reactive Messaging specification.
        Quarkus implements version 2.x of this specification but also provides many other extensions
     */

    @RestClient
    BeerService beerService;

    @Channel("beers")
    Emitter<JsonObject> emitter;

    @GET
    @Path("/emit/{beer}")
    public Response emitBeer(@PathParam("beer") int beerId) {
        beerService.getBeer(beerId)
                .map(beers -> beers.getFirst().asJsonObject())
                .subscribe().with(emitter::send);
        return Response.ok().build();
    }

}
