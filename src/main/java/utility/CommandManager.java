package utility;

import commands.Command;
import exceptions.HistoryIsEmptyException;
import exceptions.WrongAmountOfElementsException;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 12;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command insertCommand;
    private Command updateCommand;
    private Command removeKeyCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command executeScriptCommand;
    private Command exitCommand;
    private Command historyCommand;
    private Command replaceIfGreaterCommand;
    private Command removeLowerKeyCommand;
    private Command filterStartsWithNameCommand;
    private Command printUniqueLocationCommand;
    private Command printFieldDescendingWeightCommand;

    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command insertCommand,
                          Command updateCommand, Command removeKeyCommand, Command clearCommand, Command saveCommand,
                          Command executeScriptCommand, Command exitCommand, Command historyCommand,
                          Command replaceIfGreaterCommand, Command removeLowerKeyCommand, Command filterStartsWithNameCommand,
                          Command printUniqueLocationCommand, Command printFieldDescendingWeightCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.insertCommand = insertCommand;
        this.updateCommand = updateCommand;
        this.removeKeyCommand = removeKeyCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.historyCommand = historyCommand;
        this.replaceIfGreaterCommand = replaceIfGreaterCommand;
        this.removeLowerKeyCommand = removeLowerKeyCommand;
        this.filterStartsWithNameCommand = filterStartsWithNameCommand;
        this.printUniqueLocationCommand = printUniqueLocationCommand;
        this.printFieldDescendingWeightCommand = printFieldDescendingWeightCommand;

        //commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(insertCommand);
        commands.add(updateCommand);
        commands.add(removeKeyCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(historyCommand);
        commands.add(replaceIfGreaterCommand);
        commands.add(removeLowerKeyCommand);
        commands.add(filterStartsWithNameCommand);
        commands.add(printUniqueLocationCommand);
        commands.add(printFieldDescendingWeightCommand);
    }

    /**
     * @return The command history.
     */
    public String[] getCommandHistory(){
        return commandHistory;
    }

    /**
     * @return List of manager's commands.
     */
    public List<Command> getCommands(){
        return commands;
    }

    /**
     * Adds command to command history.
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore){
        for(Command command : commands){
            if (command.getName().split(" ")[0].equals(commandToStore)){
                for(int i = COMMAND_HISTORY_SIZE - 1; i>0; i--){
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }
    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status
     */
    public boolean nosuchCommand(String command){
        Console.println("Command " + command + " is not found. Type 'help' to help.");
        return false;
    }

    /**
     * Prints info about the all commands/
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument){
        if(helpCommand.execute(argument)){
            for (Command command : commands){
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        }else return false;
    }

    /**
     * Executes needs command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument){
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument){
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean insert(String argument){
        return insertCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument){
        return updateCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeKey(String argument){
        return removeKeyCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument){
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument){
        return saveCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument){
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument){
        return exitCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean history(String argument){
        if(historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();
                Console.println("Last uesd commands: ");
                for (int i = 0; i < commandHistory.length; i++) {
                    if (commandHistory[i] != null) {
                        Console.print(" " + commandHistory[i]);
                    }
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.println("No commands have been used yet!");
            }
        }
        return false;
    }

    /**
     * Executes needs command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean replaceIfGreater (String argument){
        return replaceIfGreaterCommand.execute(argument);
    }

    /**
     * Executes needs command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLowerKey (String argument){
        return removeLowerKeyCommand.execute(argument);
    }

    /**
     * Executes needs command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterStartsWithName(String argument){
        return filterStartsWithNameCommand.execute(argument);
    }

    /**
     * Executes needs command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean printUniqueLocation(String argument){
        return printUniqueLocationCommand.execute(argument);
    }

    /**
     * Executes needs command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean printFieldDescendingWeight(String argument){
        return printFieldDescendingWeightCommand.execute(argument);
    }

    @Override
    public String toString(){
        return "CommandManager (helper class for working with commands)";
    }

}
