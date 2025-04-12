package ru.ifmo.se.command;

import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;
/**
 * Command that exits the program.
 * <p>
 * This command terminates the application when executed.
 * </p>
 */
public class Exit extends Command{

    /**
     * Constructs the command with a description and command type.
     */
    public Exit() {
        super("exits program", CommandType.PRIMITIVE);
    }
    /**
     * Executes the exit command, terminating the program.
     *
     * @param request the request associated with the exit command (not used in this case)
     * @return a {@link Response} indicating that the program is exiting
     */
    @Override
    public Response execute(Request request) {
        System.exit(0);
        return new Response("exiting");
    }
}
