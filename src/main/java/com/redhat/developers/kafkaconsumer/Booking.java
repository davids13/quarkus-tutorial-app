package com.redhat.developers.kafkaconsumer;

import java.time.LocalDateTime;

public class Booking {
    public long id;
    public String from;
    public String to;
    public LocalDateTime created;

    public Booking() {
    }

    public Booking(long id, String from, String to, LocalDateTime created) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.created = created;
    }
}
