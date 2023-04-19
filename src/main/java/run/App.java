package run;

import commands.*;
import utility.*;

import java.util.Scanner;

/**
 * Main application class. Creates all instances and runs the program.
 * @author Зыонг Динь Ань reference github.
 */
public class App {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    public static void main(String[] args){
        try (Scanner userScanner = new Scanner(System.in)) {

            PersonAsker personAsker = new PersonAsker(userScanner);
            FileManager fileManager = new FileManager(args);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new InsertCommand(collectionManager, personAsker),
                    new UpdateCommand(collectionManager, personAsker),
                    new RemoveKeyCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ExecuteScriptCommand(),
                    new ExitCommand(),
                    new HistoryCommand(),
                    new ReplaceIfGreaterCommand(personAsker, collectionManager),
                    new RemoveLowerKeyCommand(personAsker, collectionManager),
                    new FilterStartsWithNameCommand(collectionManager),
                    new PrintUniqueLocationCommand(collectionManager),
                    new PrintFieldDescendingWeightCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner, personAsker);

            console.interactiveMode();
        }
    }
}
