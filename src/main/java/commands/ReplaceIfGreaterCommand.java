package commands;

import data.Person;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputScripException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.PersonAsker;

import java.time.LocalDateTime;

/**
 * Command 'replace_if_greater'. Replaces the element if it greater.
 */
public class ReplaceIfGreaterCommand extends  AbstractCommand{
    private PersonAsker personAsker;
    private CollectionManager collectionManager;

    public ReplaceIfGreaterCommand(PersonAsker personAsker, CollectionManager collectionManager) {
        super("replace_if_greater {element}", "replace value by key if new value is greater than old.");
        this.personAsker = personAsker;
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute (String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
                Person personToReplace = new Person(
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
                if(collectionManager.collectionSize() == 0 || personToReplace.compareTo(collectionManager.getFirst()) > 0){
                    collectionManager.addToCollection(personToReplace);
                    Console.println("Person successfully replace!");
                    return true;
                } else Console.printerror("The value of a person is lower than the value of the biggest of the person!");
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        }catch (IncorrectInputScripException exception){}
        return false;
    }
}
