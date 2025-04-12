package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.HashSet;
/**
 * Command that adds an element to the collection if it is greater than all existing elements.
 * <p>
 * This command compares the new element (flat) with the maximum element in the collection,
 * and only adds the new element if it is greater than the current maximum element in the collection.
 * </p>
 */
public class AddIfMax extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected AddIfMax() {
        super("adds element to the collection if it greater than existing elements", CommandType.FLAT_ENTER);
    }
    /**
     * Executes the command to add an element to the collection if it is greater than all existing elements.
     *
     * @param request the request containing the element to be added to the collection
     * @return a {@link Response} indicating the result of the operation
     */
    @Override
    public Response execute(Request request) {
        if(request.element() == null) return new Response("enter a flat to add");
        Flat newElement = request.element();
        CollectionManager flatCollectionManager = CollectionManager.getInstance();
        HashSet<Flat> collection = flatCollectionManager.getCollection();
        Flat maxFlat = newElement;
        for(Flat element : collection){
            if(maxFlat.compareTo(element) < 0) maxFlat = element;
        }

        boolean addResult = collection.add(maxFlat);
        return addResult ? new Response("element added to the collection")
                : new Response("element isn't greater than max element in collection");
    }
}
