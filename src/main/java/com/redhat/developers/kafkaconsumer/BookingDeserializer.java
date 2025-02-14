package com.redhat.developers.kafkaconsumer;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class BookingDeserializer extends JsonbDeserializer<Booking> {
    public BookingDeserializer() {
        super(Booking.class);
    }
}
