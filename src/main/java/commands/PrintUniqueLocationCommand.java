package commands;

import data.Location;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

import java.util.List;

/**
 * Command 'print_unique_location'. Display the unique values of the position field of all elements in the collection.
 */
public class PrintUniqueLocationCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public PrintUniqueLocationCommand(CollectionManager collectionManager) {
        super("print_unique_location", "display the unique values of the position field of all elements in the collection.");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            List<Location> uniqueLocations = collectionManager.uniqueLocation();

            if(!uniqueLocations.isEmpty()){
                Console.println("Unique locations:");
                for(Location location : uniqueLocations){
                    Console.println(location.getX() + " " + location.getY() + " " + location.getZ());
                }
                return true;
            } else {
                Console.println("There are no unique locations in the collection!");
            }
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception){
            Console.printerror("The collection is empty!");
        }
        return false;
    }
}
