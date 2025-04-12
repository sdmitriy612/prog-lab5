package ru.ifmo.se.io.interactors;

import java.util.Collections;
import java.util.List;
/**
 * Represents a response containing a message, optional associated objects, and optional extra data.
 * This class encapsulates the result of processing a request and can contain a message along with any relevant objects and additional data.
 */
public record Response(String message, List<Object> objects, String extra) {
    /**
     * Constructs a new {@link Response} with the specified message, no associated objects, and no extra data.
     * @param message the message to be included in the response
     */
    public Response(String message){
        this(message, Collections.emptyList(), null);
    }
    /**
     * Constructs a new {@link Response} with the specified message and associated objects, but no extra data.
     * @param message the message to be included in the response
     * @param objects the objects associated with the response
     */
    public Response(String message, List<Object> objects){
        this(message, objects, null);
    }
}
