package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

import java.util.List;

/**
 * Command 'print_field_descending_weight'. Display the values of the weight field of all elements in descending order.
 */
public class PrintFieldDescendingWeightCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public PrintFieldDescendingWeightCommand(CollectionManager collectionManager) {
        super("print_field_descending_weight", "display the values of the weight field of all elements in descending order.");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if(collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            List<Float> weights = collectionManager.getDescendingWeights();
            if(weights.isEmpty()) Console.println("No weights found in the collection!");
            else Console.println("Descending weights: " + weights);
            return true;
        } catch (WrongAmountOfElementsException exception){
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception){
            Console.printerror("The collection is empty!");
        }
        return false;
    }
}
