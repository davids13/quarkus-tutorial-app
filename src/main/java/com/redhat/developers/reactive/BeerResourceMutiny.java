package com.redhat.developers.reactive;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/beers")
public class BeerResourceMutiny {

    // curl localhost:8080/beer

    /*
        Reactive with Mutiny:
        Quarkus provides a novel reactive API called Mutiny,with the goal of easing the development
        of highly scalable, resilient, and asynchronous systems.
    */

    @RestClient
    BeerService beerService;

    @GET
    public Multi<Beer> beers() {
        return Multi.createBy().repeating()
                .uni(
                        () -> new AtomicInteger(1),
                        i -> beerService.getBeers(i.getAndIncrement())
                )
                .until(List::isEmpty)
                .onItem().<Beer>disjoint()
                .select().where(b -> b.getAbv() > 1.0);
    }
}
