package utility;

import exceptions.ScriptRecursionException;
import run.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Operates command input.
 */
public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private PersonAsker personAsker;
    private List<String> scripStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner userScanner, PersonAsker personAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.personAsker = personAsker;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode(){
        String[] userCommand = {"", ""};
        int commandStatus;
        try{
            do{
                Console.print(App.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            }while(commandStatus != 2);
        }catch (NoSuchElementException exception){
            Console.printerror("User input not found!");
        }catch (IllegalStateException exception){
            Console.printerror("Unexpected error");
        }
    }

    /**
     * Mode for catching commands from a script.
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptmode(String argument){
        String[] userCommand = {"", ""};
        int commandStatus;
        scripStack.add(argument);
        try(Scanner scriptScanner = new Scanner((new File(argument)))){
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = personAsker.getUserScanner();
            personAsker.setUserScanner(scriptScanner);
            personAsker.setFileMode();
            do{
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while(scriptScanner.hasNextLine() && userCommand[0].isEmpty()){
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ",2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(App.PS1 + String.join(" ", userCommand));
                if(userCommand[0].equals("execute_script")){
                    for(String script : scripStack){
                        if(userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            }while(commandStatus == 0 && scriptScanner.hasNextLine());
            personAsker.setUserScanner(tmpScanner);
            personAsker.setUserMode();
            if(commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Check the script for correctness of the entered data!");
            return commandStatus;
        }catch (FileNotFoundException exception){
            Console.printerror("Script file not found!");
        }catch (NoSuchElementException exception){
            Console.printerror("Script file is empty!");
        }catch (ScriptRecursionException exception){
            Console.printerror("Script cannot be called recursively!");
        }catch (IllegalStateException exception){
            Console.printerror("Unexpected error");
            System.exit(0);
        }finally{
            scripStack.remove(scripStack.size() - 1);
        }
        return 1;
    }

    /**
     * Launch the command.
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int launchCommand(String[] userCommand){
        switch (userCommand[0]){
            case "":
                break;
            case "help":
                if(!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if(!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if(!commandManager.show(userCommand[1])) return 1;
                break;
            case "insert":
                if(!commandManager.insert(userCommand[1])) return 1;
                break;
            case "update":
                if(!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_key":
                if(!commandManager.removeKey(userCommand[1])) return 1;
                break;
            case "clear":
                if(!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if(!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if(!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptmode(userCommand[1]);
            case "history":
                if(!commandManager.history(userCommand[1])) return 1;
                break;
            case "replace_if_greater":
                if(!commandManager.replaceIfGreater(userCommand[1])) return 1;
                break;
            case "remove_lower_key":
                if(!commandManager.removeLowerKey(userCommand[1])) return 1;
                break;
            case "filter_starts_with_name":
                if(!commandManager.filterStartsWithName(userCommand[1])) return 1;
                break;
            case "print_unique_location":
                if(!commandManager.printUniqueLocation(userCommand[1])) return 1;
                break;
            case "print_field_descending_weight":
                if(!commandManager.printFieldDescendingWeight(userCommand[1])) return 1;
                break;
            case "exit":
                if(!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if(!commandManager.nosuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }

    /**
     * Print error: toOUt.toString() +\n Console.
     * @param toOut to print.
     */
    public static void print(String toOut){
        System.out.print(toOut);
    }

    /**
     * Print error: toOUt.toString() +\n Console.
     * @param toOut to print.
     */
    public static void println(String toOut){
        System.out.println(toOut);
    }
    public static void println(CollectionManager toOut){
        System.out.println(toOut);
    }
    /**
     * Print error: toOUt.toString() +\n Console.
     * @param toOut to print.
     */
    public static void printerror(String toOut){
        System.out.println("Error: " + toOut);
    }
    /**
     * Prints formatted 2-element table to Console.
     * @param element1 left element of the row.
     * @param element2 right element of the row.
     */
    public static void printtable(String element1, String element2){
        System.out.printf("%-37s%-1s%n", element1, element2);
    }

    @Override
    public String toString(){
        return "Console (class for handling command input)";
    }
}
