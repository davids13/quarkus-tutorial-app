package com.redhat.developers;

import com.redhat.developers.security.MyService;
import com.redhat.developers.security.MyServiceCredentials;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest
class ClientBasicAuthUnitTest {

    @Test
    void whenNoCredentialsProvided_then401StatusCode() {
        MyService myService = RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8081"))
                .build(MyService.class);

        try {
            myService.hello();
        } catch (Exception e) {
            if (e.getCause() instanceof WebApplicationException webApp) {
                assertEquals(401, webApp.getResponse().getStatus());
                return;
            }
        }

        fail("Should have thrown a exception with 401");
    }

    @Test
    void whenRightCredentialsProvided_thenResponseReceived() {
        MyService myService = RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8081"))
                .build(MyServiceCredentials.class);

        assertEquals("Hello from Quarkus REST", myService.hello());
    }
}
