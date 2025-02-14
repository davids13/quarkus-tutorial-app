package com.redhat.developers.reactive;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

public class ChannelAndEmitter {

    @Inject
    @Channel("prices") // indicate to which channel you are going to send your payload or messages
    Emitter<Double> emitter; // The Emitter is the object to use to send these payloads or messages.

    public void emit(double d) {
        emitter.send(d);
    }

    @Incoming("prices") // Listen to events from prices channel; here, we are going to receive the message sent by the emitter
    public void process() {}
}
