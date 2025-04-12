package ru.ifmo.se.command;

import ru.ifmo.se.handling.Router;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;
/**
 * Command that displays the history of previously executed commands.
 * <p>
 * This command retrieves and displays the list of commands that have been executed
 * previously in the current session.
 * </p>
 */
public class History extends Command{
    /**
     * Constructs the command with a description and command type.
     */
    public History(){
        super("displays history of commands", CommandType.PRIMITIVE);
    }
    /**
     * Executes the history command, displaying the history of commands that have been executed.
     *
     * @param request the request associated with the history command (not used in this case)
     * @return a {@link Response} containing the history of commands as a string
     */
    @Override
    public Response execute(Request request) {
        return new Response("Last commands:\n" + String.join("\n", Router.getCommandsHistory()));
    }
}
