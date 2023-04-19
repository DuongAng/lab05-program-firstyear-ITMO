package commands;

/**
 * Abstract Command class contains Object methods, name and description
 */
public abstract class AbstractCommand implements Command {
    private String name;
    private String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return Description of the command.
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return name + " (" + description + ") ";
    }
}
