package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.HashSet;
/**
 * Command that removes all elements from the collection that are "lower" than the given element
 * based on a comparison.
 * <p>
 * This command iterates through the collection and removes all elements where the comparison
 * of their values is less than the given element.
 * </p>
 */
public class RemoveLower extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected RemoveLower() {
        super("removes one element which view is equals to given", CommandType.FLAT_ENTER);
    }
    /**
     * Executes the remove lower command.
     * It removes all elements in the collection that are considered "lower" than the provided element
     * based on a comparison of their values.
     *
     * @param request the request containing the element to compare with
     * @return a {@link Response} indicating the result of the operation
     */
    @Override
    public Response execute(Request request) {
        if(request.element() == null) return new Response("enter a flat to compare");
        Flat comparingElement = request.element();
        CollectionManager flatCollectionManager = CollectionManager.getInstance();
        HashSet<Flat> collection = flatCollectionManager.getCollection();

        boolean removeResult = collection.removeIf(element -> element.compareTo(comparingElement) < 0);
        return removeResult ? new Response("elements lower than given were deleted")
                : new Response("collection doesn't include elements lower than given");
    }
}