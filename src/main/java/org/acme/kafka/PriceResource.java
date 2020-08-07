package org.acme.kafka;

import io.smallrye.reactive.messaging.annotations.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;

/**
 * A simple resource retrieving the in-memory "my-data-stream" and sending the items as server-sent events.
 */
@Path("/prices")
public class PriceResource {

    @Inject
    // Injects the my-data-stream channel using the @Channel qualifier
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Path("/stream")
    // Indicates that the content is sent using Server Sent Events
    @Produces(MediaType.SERVER_SENT_EVENTS)
    // Indicates that the data contained within the server sent events is of type text/plain
    @SseElementType("text/plain")
    public Publisher<Double> stream() {
        // Returns the stream (Reactive Stream)
        return prices;
    }
}