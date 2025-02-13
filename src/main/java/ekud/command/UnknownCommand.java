package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

/**
 * Represents an unknown or invalid command entered by the user.
 */
public class UnknownCommand extends Command {
    /**
     * Constructs an {@code UnknownCommand} with the given user input.
     *
     * @param input The unrecognized user command.
     */
    public UnknownCommand(String input) {
        super(input);
    }

    /**
     * Executes the unknown command.
     * <p>
     * This method notifies the user that the entered command is unrecognized.
     * </p>
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance (not used in this command).
     * @return A message indicating that the command is unknown.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasks object does not exist";
        assert ui != null : "UI object does not exist";
        assert storage != null : "Storage object does not exist";
        super.execute(tasks, ui, storage);
        return ui.showUnknown();
    }

}
