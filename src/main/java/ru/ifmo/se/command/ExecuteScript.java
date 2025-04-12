package ru.ifmo.se.command;

import ru.ifmo.se.handling.Handler;
import ru.ifmo.se.handling.WrongScriptDataError;
import ru.ifmo.se.io.files.FileIOController;
import ru.ifmo.se.io.interactors.Request;
import ru.ifmo.se.io.interactors.Response;

import java.io.IOException;
/**
 * Command that reads and executes a script from a given file.
 * <p>
 * This command processes the file provided in the argument and executes each line as a command.
 * It also ensures that script execution is not recursive.
 * </p>
 */
public class ExecuteScript extends Command{
    private static int recursionCount = 0;
    private final int recursionLimit = 5;
    /**
     * Constructs the command with a description and command type.
     */
    protected ExecuteScript() {
        super("reads and executes a script from a given file", CommandType.PRIMITIVE);
    }
    /**
     * Executes the script from the file provided in the request argument.
     * The script is executed line by line, with each line processed as a command.
     * <p>
     * If the script tries to execute another script, an error message is returned.
     * </p>
     *
     * @param request the request containing the file name to execute the script from
     * @return a {@link Response} indicating the result of the script execution
     */
    @Override
    public Response execute(Request request) {
        if(request.args() == null || request.args().size() != 1) return new Response("enter a file name");
        StringBuilder answers = new StringBuilder();

        try(FileIOController fileIOController = new FileIOController(request.args().get(0))){
            recursionCount++;

            String line;
            while ((line = fileIOController.read()) != null){
                if(recursionCount > recursionLimit) return new Response("recursion limit exceed");
                answers.append(Handler.handle(line, fileIOController)).append(System.lineSeparator());
            }

        }catch (IOException | WrongScriptDataError e){
            return new Response("Executing script error: " + e.getMessage());
        }

        recursionCount--;
        return new Response(answers.append("script has ended").toString());

    }

}
