package commands;

import data.Color;
import data.Coordinates;
import data.Location;
import data.Person;
import exceptions.*;
import utility.CollectionManager;
import utility.Console;
import utility.PersonAsker;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private PersonAsker personAsker;

    public UpdateCommand(CollectionManager collectionManager, PersonAsker personAsker) {
        super("update <ID> {element}", "update collection element value by ID.");
        this.collectionManager = collectionManager;
        this.personAsker = personAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     * @param argument
     */
    @Override
    public boolean execute(String argument){
        try{
            if(argument.isEmpty()) throw new WrongAmountOfElementsException();
            if(collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int id = Integer.parseInt(argument);
            Person oldPerson = collectionManager.getByKey(id);
            if(oldPerson == null) throw new PersonNotFoundException();

            String name = oldPerson.getName();
            Coordinates coordinates = oldPerson.getCoordinates();
            LocalDateTime creationDate = oldPerson.getCreationDate();
            float height = oldPerson.getHeight();
            ZonedDateTime birthday = oldPerson.getBirthday();
            int weight = oldPerson.getWeight();
            Color hairColor = oldPerson.getHairColor();
            Location location = oldPerson.getLocation();

            collectionManager.removeFromCollection(oldPerson);

            if(personAsker.askQuestion("Want to change the person's name?")) name = personAsker.askName();
            if(personAsker.askQuestion("Want to change the coordinates of a person?")) coordinates = personAsker.askCoordinates();
            if(personAsker.askQuestion("Want to change the person's height?")) height = personAsker.askHeight();
            if(personAsker.askQuestion("Want to change the person's birthday?")) birthday = personAsker.askBirthday();
            if(personAsker.askQuestion("Want to change the person's weight?")) weight = personAsker.askWeight();
            if(personAsker.askQuestion("Want to change the person's hair color?")) hairColor = personAsker.askColor();
            if(personAsker.askQuestion("Want to change the person's location?")) location = personAsker.askLocation();

            collectionManager.addToCollection(new Person(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    height,
                    birthday,
                    weight,
                    hairColor,
                    location
            ));
            Console.println("Person successfully changed!");
            return true;
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception){
            Console.printerror("Collection is empty!");
        } catch (NullPointerException exception){
            Console.printerror("ID must be represented by a number!");
        } catch (PersonNotFoundException exception){
            Console.printerror("There is no person with this ID in the collection!");
        } catch (IncorrectInputScripException e) {}
        return false;
    }
}
