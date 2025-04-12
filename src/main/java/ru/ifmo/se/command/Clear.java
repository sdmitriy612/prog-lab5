package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

/**
 * Command that clears the entire collection.
 * <p>
 * This command removes all elements from the collection.
 * </p>
 */
public class Clear extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected Clear() {
        super("clears the collection", CommandType.PRIMITIVE);
    }
    /**
     * Executes the command to clear the collection.
     *
     * @param request the request to execute the command (not used in this case)
     * @return a {@link Response} indicating that the collection has been cleared
     */
    @Override
    public Response execute(Request request) {
        CollectionManager.getInstance().getCollection().clear();
        return new Response("collection has been cleaned");
    }
}