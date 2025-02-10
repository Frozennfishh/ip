package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;


/**
 * Represents a command to search for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructs a {@code FindCommand} with the given search keyword.
     *
     * @param input The keyword used to search for tasks.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the find command.
     * <p>
     * Searches for tasks that contain the given keyword and returns a formatted list of matching tasks.
     * </p>
     *
     * @param tasks   The task list to search in.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance (not used in this command).
     * @return A formatted string of matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        return ui.findTaskPrint(this.getTasks().findTask(this.getInput()));
    }
}
