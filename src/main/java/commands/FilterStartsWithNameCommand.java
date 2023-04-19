package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'filter_starts_with_name'. Display elements whose name field value starts with the given substring.
 */
public class FilterStartsWithNameCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super("filter_starts_with_name", "display elements whose name field value starts with the given substring.");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     * @param argument Name of the command.
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if(collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            String nameToFilter = argument.trim();
            String filterInfo = collectionManager.personFilterInfo(nameToFilter);
            if (!filterInfo.isEmpty()){
                Console.println(filterInfo);
                return true;
            } else Console.println("There is no person with the same name in the  collection!");
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception){
            Console.printerror("The collection is empty!");
        }
        return false;
    }
}
