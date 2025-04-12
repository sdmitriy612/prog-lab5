package ru.ifmo.se.io.interactors;

import ru.ifmo.se.collection.flat.Flat;

import java.util.Collections;
import java.util.List;
/**
 * Represents a request containing the command name, arguments, and optional associated data.
 * This class encapsulates a command to be processed along with its arguments and the associated element.
 *
 */
public record Request(String commandName, List<String> args, Flat element) {
    /**
     * Constructs a new {@link Request} with the specified command name, no arguments, and no associated element.
     * This constructor is useful for commands that do not require arguments or an element.
     * @param commandName the name of the command
     */
    public Request(String commandName){
        this(commandName, Collections.emptyList(), null);
    }
    /**
     * Constructs a new {@link Request} with the specified command name and arguments, but no associated element.
     * @param commandName the name of the command
     * @param args the arguments associated with the command
     */
    public Request(String commandName, List<String> args){
        this(commandName, args, null);
    }
}
