package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Todo;
import ekud.ui.Ui;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class TodoCommand extends Command {
    /**
     * Constructs a {@code TodoCommand} with the given user input.
     *
     * @param input The user input specifying the to-do task description.
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Executes the to-do command.
     * <p>
     * If no task description is provided, it returns an error message.
     * Otherwise, it adds the to-do task to the task list and updates storage.
     * </p>
     *
     * @param tasks   The task list where the to-do will be added.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance for saving the updated task list.
     * @return A response message indicating whether the to-do task was successfully added.
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
        assert this.getTasks() != null : "TaskList object was not created properly";
        assert this.getStorage() != null : "Storage file does not exist";
        return ui.taskAdded("Todo") + "\n"
                + this.getTasks().add(new Todo(this.getInput(), 0), this.getStorage());
    }
}
