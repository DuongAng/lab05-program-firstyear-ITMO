package commands;

import data.Person;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputScripException;
import exceptions.PersonNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.PersonAsker;

import java.time.LocalDateTime;

/**
 * Command 'remove_lower_key'. Removes the elements if it lower.
 */
public class RemoveLowerKeyCommand extends AbstractCommand{
    public PersonAsker personAsker;
    public CollectionManager collectionManager;

    public RemoveLowerKeyCommand(PersonAsker personAsker, CollectionManager collectionManager) {
        super("remove_lower_key", "remove from the collection all elements whose key is less than the given one");
        this.personAsker = personAsker;
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
            if(collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Person personToFind = new Person(
                    collectionManager.generateNextId(),
                    personAsker.askName(),
                    personAsker.askCoordinates(),
                    LocalDateTime.now(),
                    personAsker.askHeight(),
                    personAsker.askBirthday(),
                    personAsker.askWeight(),
                    personAsker.askColor(),
                    personAsker.askLocation()
            );
            Person personFromCollection = collectionManager.getByValue(personToFind);
            if (personFromCollection == null) throw new PersonNotFoundException();
            collectionManager.removeLower(personFromCollection);
            Console.println("Person successfully remove from the collection!");
            return true;
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception){
            Console.printerror("The collection is empty!");
        } catch (PersonNotFoundException exception){
            Console.printerror("Person with this ID isn't fount");
        } catch (IncorrectInputScripException exception){}
        return false;
    }
}
