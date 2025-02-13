package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

/**
 * Represents a user command in the Ekud task manager.
 * <p>
 * This is the base class for all commands, which process and execute different user actions.
 * </p>
 */
public class Command {
    private String input;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a command with the given user input.
     *
     * @param input The user command input.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command.
     * <p>
     * This method is intended to be overridden by subclasses to define specific command behaviors.
     * </p>
     *
     * @param tasks   The task list.
     * @param ui      The user interface instance.
     * @param storage The storage handler.
     * @return A response message indicating the result of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        return null;
    }

    /**
     * Returns the user input.
     *
     * @return The command input string.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Placeholder method to be overridden by subclasses if needed.
     *
     * @return A string result (default: {@code null}).
     */
    public String getString() {
        return null;
    }

    /**
     * Returns the associated task list.
     *
     * @return The task list.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Returns the storage handler.
     *
     * @return The storage instance.
     */
    public Storage getStorage() {
        return this.storage;
    }
}
