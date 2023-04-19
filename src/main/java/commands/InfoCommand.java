package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager){
        super("info", "display information about the collection");
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
            LocalDateTime lasInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lasInitTime == null) ? "initialization has" +
                    "not yet taken place in this session." : lasInitTime.toLocalDate()
                    .toString() +" " + lasInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "this session has not" +
                    " yet been saved." : lastSaveTime.toLocalDate().toString() + " " +
                    lastSaveTime.toLocalTime().toString();

            Console.println("Collection details:");
            Console.println(" Type: " + collectionManager.collectionType());
            Console.println(" Amount of elements: " + collectionManager.collectionSize());
            Console.println(" Last save date: " +  lastSaveTimeString);
            Console.println(" Date of last initialization: " + lastInitTimeString);
            return true;
        }catch(WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        }
        return false;
    }
}
