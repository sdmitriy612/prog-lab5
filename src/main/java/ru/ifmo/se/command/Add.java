package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;
/**
 * Command that adds an element to the collection.
 * <p>
 * This command adds a new element to the collection managed by {@link CollectionManager}.
 * </p>
 */
public class Add extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected Add() {
        super("adds element to the collection", CommandType.FLAT_ENTER);
    }
    /**
     * Executes the command to add an element to the collection.
     *
     * @param request the request containing the element to be added to the collection
     * @return a {@link Response} indicating the result of the operation
     */
    @Override
    public Response execute(Request request) {
        if(request.element() == null) return new Response("enter a flat to add");
        CollectionManager.getInstance().getCollection().add(request.element());
        return new Response("element added to the collection");
    }
}
