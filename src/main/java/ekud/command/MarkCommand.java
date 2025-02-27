package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;


/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a {@code MarkCommand} with the given user input.
     *
     * @param input The user input specifying the index of the task to mark as done.
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the mark command.
     * <p>
     * If the input is missing, it returns an error message. If the input index is invalid,
     * it notifies the user that the task does not exist. Otherwise, it marks the task as completed
     * and updates the storage.
     * </p>
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance for saving the updated task list.
     * @return A response message indicating whether the task was successfully marked as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        assert tasks != null : "Tasks object does not exist";
        assert ui != null : "UI object does not exist";
        assert storage != null : "Storage object does not exist";
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        }
        //checks if the input index is within the range of the tasklist array
        if (!Parser.isValidIndex(this.getInput(), tasks)) {
            return ui.taskDoesNotExist();
        }
        assert this.getTasks() != null : "TaskList object was not created properly";
        assert this.getStorage() != null : "Storage file does not exist";
        String temp = ui.markDone(this.getTasks(), Integer.parseInt(this.getInput()) - 1);
        storage.saveToFile(this.getTasks());
        return temp;
    }
}
