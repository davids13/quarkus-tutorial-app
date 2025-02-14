package com.redhat.developers.reactive;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

public class BroadcastDemo {

    @Incoming("receiving message")
    @Outgoing("sending messages")
    //@Broadcast // using @BroadcastBy you can tell the system to send messages to all the consumers in the channel.
    @Broadcast(1) // Sets the number of consumers. If not set then all consumers receive the message.
    public int increment(int i) {
        return i + 1;
    }

    @Incoming("out")
    public void consume1(int i) {
        //...
    }

    @Incoming("out")
    public void consume2(int i) {
        //...
    }

    // both consumers get the messages.
}
