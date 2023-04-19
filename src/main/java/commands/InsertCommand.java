package commands;

import data.Person;
import exceptions.IncorrectInputScripException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.PersonAsker;

import java.time.LocalDateTime;

/**
 * Command 'insert null'. Add a new element to collection.
 */
public class InsertCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private PersonAsker personAsker;

    public InsertCommand(CollectionManager collectionManager, PersonAsker personAsker) {
        super("insert null {element}", "add a new element with given key.");
        this.collectionManager = collectionManager;
        this.personAsker = personAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new Person(
                    collectionManager.generateNextId(),
                    personAsker.askName(),
                    personAsker.askCoordinates(),
                    LocalDateTime.now(),
                    personAsker.askHeight(),
                    personAsker.askBirthday(),
                    personAsker.askWeight(),
                    personAsker.askColor(),
                    personAsker.askLocation()
                    ));
            Console.println("Person successfully insert!");
            return true;
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        } catch (IncorrectInputScripException exception){}
        return false;
    }
}