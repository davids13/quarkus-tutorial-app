package com.redhat.developers.restclient;

import com.redhat.developers.entity.FruityVice;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/fruit")
@RegisterRestClient(baseUri = "https://sbc.com")
public interface FruityViceService {

    /*
        Fault tolerance:
            - @Retry(): Now in case of any error, 3 retries are done automatically, waiting for 2 seconds between retries.
            - Fallback(): Now in case of any error, 3 retries are done automatically, waiting for 2 seconds between retries.
                          If the error persists, then the fallback method is executed.
                          Now after waiting for 6 seconds (3 retries x 2 seconds), an empty object is sent instead of an exception.
            - @CircuitBreaker(): Now, if 3 (4 x 0.75) failures occur among the rolling window of 4 consecutive invocations,
                                 then the circuit is opened for 5000 ms and then will be back to half open.
                                 If the invocation succeeds, then the circuit is back to closed again.
    */

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 2000) // Now in case of any error, 3 retries are done automatically, waiting for 2 seconds between retries.
    @Fallback(FruityViceFallback.class)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 5000)
    FruityVice getFruitByName(@PathParam("name") String name);

    class FruityViceFallback implements FallbackHandler<FruityVice> {
        private static final FruityVice EMPTY_FRUITY_VICE = FruityVice.of("empty", FruityVice.Nutritions.of(0.0, 0.0));

        @Override
        public FruityVice handle(ExecutionContext context) {
            return EMPTY_FRUITY_VICE;
        }

    }
}
