package com.redhat.developers.reactive;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class BeerProcessorKafka {

    @Incoming("beers") // listen events from beers channel
    @Outgoing("messages") // sends/emits the result of the method call to the messages channel
    @Retry(maxRetries = 3, delay = 2000) // Now in case of any error, 3 retries are done automatically, waiting for 2 seconds between retries.
    public JsonObject processPrize(JsonObject beer) { // Argument is the message of the beers channel
        JsonObjectBuilder beerWithPrize = Json.createObjectBuilder(beer).add("price", getPrice());
        return beerWithPrize.build(); // Return object is sent to the messages channel
    }

    private int getPrice() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }
    
}
