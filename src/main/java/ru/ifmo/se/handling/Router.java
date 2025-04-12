package ru.ifmo.se.handling;

import ru.ifmo.se.command.Command;
import ru.ifmo.se.command.CommandsMap;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.util.ArrayDeque;
import java.util.HashMap;
/**
 * The Router class is responsible for routing incoming requests to the appropriate command.
 * <p>
 * It maintains a history of executed commands.
 * </p>
 */
public class Router {
    private final static ArrayDeque<String> commandsHistory = new ArrayDeque<>();
    private final static int maxHistorySize = 12;
    /**
     * Routes the incoming request to the appropriate command and executes it.
     * The command is executed based on the request's command name, and the command's response is returned.
     * The command is also added to the command history (if it's not {@code /history} command).
     *
     * @param request the request object containing the command name and arguments
     * @return the response after executing the command
     */
    public static Response route(Request request){
        HashMap<String, Command> commandsMap = CommandsMap.getCommands();

        if(commandsHistory.size() == maxHistorySize) commandsHistory.removeFirst();
        if(!request.commandName().equals("/history")) commandsHistory.addLast(request.commandName());

        return commandsMap.get(request.commandName()).execute(request);
    }

    public static ArrayDeque<String> getCommandsHistory(){
        return commandsHistory;
    }
}
