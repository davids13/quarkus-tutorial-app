package com.redhat.developers.kafkaconsumer;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.resteasy.reactive.RestSseElementType;
import org.reactivestreams.Publisher;

@Path("/bookings")
@ApplicationScoped
public class BookingResource {

    @Inject
    @Channel("bookings-stream")
    Publisher<Booking> reactiveBookings;

    @Incoming("bookings")
    @Outgoing("bookings-stream")
    @Broadcast
    public Booking process(KafkaRecord<Long, Booking> msg) {
        System.out.println(">> message received " + msg.toString());
        return msg.getPayload();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    //@SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<Booking> stream() {
        System.out.println(">> called");
        return reactiveBookings;
    }
}
