package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.HashSet;
/**
 * Command that removes one element from the collection where the ID matches the specified value.
 * <p>
 * This command iterates through the collection and removes the first element
 * that matches the given ID.
 * </p>
 */
public class RemoveByID extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected RemoveByID() {
        super("removes one element which id is equals to given", CommandType.PRIMITIVE);
    }
    /**
     * Executes the remove by ID command.
     * It removes the first element in the collection whose ID matches the provided value.
     *
     * @param request the request containing the ID of the element to be removed
     * @return a {@link Response} indicating the result of the operation
     */
    @Override
    public Response execute(Request request) {
        if(request.args() == null || request.args().isEmpty()) return new Response("enter an id to remove");
        if(request.args().size() > 1) return new Response("enter only id");
        long id;
        try{
            id = Long.parseLong(request.args().get(0));
        }catch (NumberFormatException e){
            return new Response("id must be digit");
        }

        HashSet<Flat> collection = CollectionManager.getInstance().getCollection();

        boolean removeResult = collection.removeIf(element -> element.getId() == id);
        return new Response(removeResult ? "was deleted element with id " +  id
                : "collection doesn't include elements with id " + id);

    }
}
