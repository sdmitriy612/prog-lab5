package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.HashSet;
/**
 * Command that updates the value of an item in the collection based on the given id.
 * <p>
 * This command looks for an element with the specified id and replaces it with the provided element
 * if such an element exists in the collection.
 * </p>
 */
public class Update extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected Update() {
        super("updates the value of the collection item whose id is equal to the given one",
                CommandType.FLAT_ENTER);
    }
    /**
     * Executes the command to update an element in the collection by its id.
     *
     * @param request the request object containing the id and the element to update
     * @return a {@link Response} indicating the result of the operation (either success or failure)
     */
    @Override
    public Response execute(Request request) {
        if(request.args() == null || request.args().isEmpty()) return new Response("enter an id to update");
        if(request.args().size() > 1) return new Response("enter only id");

        long id;
        try{
            id = Long.parseLong(request.args().get(0));
        }catch (NumberFormatException e){
            return new Response("id must be digit");
        }

        if(request.element() == null) return new Response("enter a flat to add");
        Flat newElement = request.element();
        newElement.setId(id);

        HashSet<Flat> collection = CollectionManager.getInstance().getCollection();

        boolean removeResult = collection.removeIf(element -> element.getId() == id);
        if(removeResult){
            collection.add(newElement);
            return new Response("element was updated");
        }
        return new Response("collection doesn't include elements with id " + id);

    }
}