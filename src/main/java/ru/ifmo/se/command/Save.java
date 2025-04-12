package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;
/**
 * Command that saves the current collection to an XML file.
 * <p>
 * This command invokes the {@code save()} method from the {@link CollectionManager} to persist the
 * collection into a file specified by the environment variable.
 * </p>
 */
public class Save extends Command {
    /**
     * Constructs the command with a description and command type.
     */
    protected Save() {
        super("saves collection to XML file", CommandType.PRIMITIVE);
    }
    /**
     * Executes the save command, which saves the current collection to an XML file.
     *
     * @param request the request object containing any arguments (not used in this case)
     * @return a {@link Response} indicating the success of the operation
     */
    @Override
    public Response execute(Request request) {
        CollectionManager.getInstance().save();
        return new Response("collection has been saved to file");

    }
}
