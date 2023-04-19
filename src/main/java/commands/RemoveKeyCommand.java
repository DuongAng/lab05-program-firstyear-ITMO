package commands;


import data.Person;
import exceptions.CollectionIsEmptyException;
import exceptions.PersonNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;



/**
 * Command 'remove_key'. Removes the element by its ID.
 */
public class RemoveKeyCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveKeyCommand(CollectionManager collectionManager) {
        super("remove_key <ID>", "removes element from collection by ID.");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            Person personToRemove = collectionManager.getByKey(id);
            if (personToRemove == null) throw new PersonNotFoundException();
            collectionManager.removeFromCollection(personToRemove);
            Console.println("Person successfully removed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        } catch (NumberFormatException exception){
            Console.printerror("ID must be a number!");
        } catch (PersonNotFoundException exception){
            Console.printerror("Person with that ID not found!");
        }
        return false;
    }
}