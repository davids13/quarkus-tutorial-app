package com.redhat.developers.observability;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Instant;
import java.util.Calendar;
import java.util.TimeZone;

@Path("/time")
public class TimeResource {

    // http://localhost:8080/q/metrics

    // Let’s add a custom counter that counts how many times a particular method has been called.

    private final MeterRegistry registry;

    public TimeResource(MeterRegistry registry) {
        this.registry = registry;
        registry.gauge("offsetFromUTC", this, TimeResource::offsetFromUTC);
    }

    @Counted(value = "time.now")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Instant now() {
        return Instant.now();
    }

    int offsetFromUTC() {
        return TimeZone.getDefault().getOffset(Calendar.ZONE_OFFSET) / (3600 * 1000);
    }
}
