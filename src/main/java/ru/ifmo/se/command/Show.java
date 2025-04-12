package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.ArrayList;
import java.util.HashSet;
/**
 * Command that displays all the elements in the collection.
 * <p>
 * This command retrieves all elements from the collection managed by {@link CollectionManager}
 * and formats them into a string representation for display.
 * </p>
 */
public class Show extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    public Show(){
        super("displays all collection elements", CommandType.PRIMITIVE);
    }
    /**
     * Executes the command to display all elements in the collection.
     *
     * @param request the request object containing any arguments (not used in this case)
     * @return a {@link Response} containing the string representation of all elements in the collection
     */
    @Override
    public Response execute(Request request) {
        HashSet<Flat> collection = CollectionManager.getInstance().getCollection();
        ArrayList<String> collectionStrings = new ArrayList<>();
        collection.forEach(flat -> {
            collectionStrings.add(flat.toString());
        });
        return new Response(collectionStrings.isEmpty() ? "collection is empty" : String.join(System.lineSeparator(), collectionStrings));
    }
}
