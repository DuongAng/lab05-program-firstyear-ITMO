package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'execute_script'. Executes scripts from a file. Actually only checks argument and prints messages.
 */
public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand(){
        super("execute_script <file_name>", "execute script from specified file");
    }

    /**
     * Executes the command, but partially.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if ( argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Executing a script '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        }
        return false;
    }
}
