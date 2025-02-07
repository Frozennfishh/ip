package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command{
    /**
     * Constructs a {@code DeleteCommand} with the given user input.
     *
     * @param input The user input specifying the task index to delete.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to delete a task.
     * <p>
     * If no input is provided, it returns an error message. If the input index is invalid,
     * it notifies the user that the task does not exist. Otherwise, the task is deleted.
     * </p>
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance for saving the updated task list.
     * @return A response message indicating the result of the deletion.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        } else {
            if (Parser.isValidIndex(this.getInput(), tasks)) {
                return ui.taskDoesNotExist();
            } else {
                return ui.delete(tasks, Integer.parseInt(this.getInput()) - 1, storage);
            }
        }
    }
}
