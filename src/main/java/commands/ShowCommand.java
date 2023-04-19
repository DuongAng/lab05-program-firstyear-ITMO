package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'show'. Show information about all elements of the collection.
 */
public class ShowCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager){
        super("show", "display all elements of the collection");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println(collectionManager);
            return true;
        }catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        }
        return false;
    }
}
