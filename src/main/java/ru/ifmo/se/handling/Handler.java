package ru.ifmo.se.handling;

import ru.ifmo.se.collection.flat.Flat;
import ru.ifmo.se.command.Command;
import ru.ifmo.se.command.CommandType;
import ru.ifmo.se.command.CommandsMap;
import ru.ifmo.se.io.StringIOController;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * The Handler class processes incoming requests and routes them to the appropriate command for execution.
 * It interacts with the {@link StringIOController} to handle user input and generates a response.
 * <p>
 *     If command is {@code FLAT_ENTER} type, starts input of element.
 * </p>
 *
 * @see Router
 */
public class Handler {
    /**
     * Handles the incoming request, processes the command, and returns the response message.
     *
     * @param requestString the input string representing the user's request
     * @param stringIOController the controller for reading and writing user input/output
     * @return the response message after processing the command
     */
    public static String handle(String requestString, StringIOController stringIOController){

        if (requestString.isBlank()) return "enter correct request";

        ArrayList<String> requestData = new ArrayList<>(List.of(requestString.split(" ")));
        String commandName = requestData.remove(0);
        HashMap<String, Command> commands = CommandsMap.getCommands();
        if(!commands.containsKey(commandName)) return "unknown command. Use /help for command list.";

        Flat flat = null;
        ElementInputer elementInputer = new ElementInputer();

        if(commands.get(commandName).getCommandType() == CommandType.FLAT_ENTER){
            flat = elementInputer.inputFlat(stringIOController);
        }
        Request request = new Request(commandName, requestData, flat);
        Response response = Router.route(request);

        return response.message();
    }
}
