package ru.ifmo.se.command;

import ru.ifmo.se.collection.CollectionManager;
import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.collection.flat.View;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.HashSet;
/**
 * Command that removes one element from the collection where the view equals the specified value.
 * <p>
 * This command iterates through the collection and removes the first element
 * that matches the provided view.
 * </p>
 */
public class RemoveAnyByView extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    protected RemoveAnyByView() {
        super("removes one element which view is equals to given", CommandType.PRIMITIVE);
    }
    /**
     * Executes the remove by view command.
     * It removes the first element in the collection whose view matches the provided value.
     *
     * @param request the request containing the view to be matched and removed
     * @return a {@link Response} indicating the result of the operation
     */
    @Override
    public Response execute(Request request) {
        if(request.args() == null || request.args().isEmpty()) return new Response("enter a view to count");
        if(request.args().size() > 1) return new Response("enter 1 view");

        View view;
        try{
            view = View.valueOf(request.args().get(0));
        }catch (IllegalArgumentException e){
            return new Response("entered view doesn't exist");
        }

        HashSet<Flat> collection = CollectionManager.getInstance().getCollection();

        for(Flat element : collection){
            if(element.getView() == view) {
                collection.remove(element);
                return new Response("was deleted element with view " + view);
            }
        }
        return new Response("collection doesn't include elements with view " + view);

    }
}
