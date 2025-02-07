package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand} with the given user input.
     *
     * @param input The user input (not used in this command).
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the exit command.
     * <p>
     * This method returns a farewell message, signaling the termination of the program.
     * </p>
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance (not used in this command).
     * @return A farewell message from the UI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodbye();
    }
}
