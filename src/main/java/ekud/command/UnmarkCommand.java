package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

/**
 * Represents a command to unmark a task as incomplete in the task list.
 */
public class UnmarkCommand extends Command{
    /**
     * Constructs an {@code UnmarkCommand} with the given user input.
     *
     * @param input The user input specifying the index of the task to unmark.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the unmark command.
     * <p>
     * If the input is missing, it returns an error message. If the input index is invalid,
     * it notifies the user that the task does not exist. Otherwise, it marks the task as incomplete
     * and updates storage.
     * </p>
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance for saving the updated task list.
     * @return A response message indicating whether the task was successfully unmarked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        }
        if (Parser.isValidIndex(this.getInput(), tasks)) {
            return ui.taskDoesNotExist();
        } else {
            String temp = ui.markUndone(tasks, Integer.parseInt(this.getInput()) - 1);
            storage.saveToFile(tasks);
            return temp;
        }
    }
}
