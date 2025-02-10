package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

/**
 * Represents a command that clears all tasks from the task list.
 */
public class ClearCommand extends Command {

    /**
     * Constructs a ClearCommand object.
     *
     * @param input The user input that triggered this command.
     */
    public ClearCommand(String input) {
        super(input);
    }

    /**
     * Executes the clear command by removing all tasks from the task list,
     * saving the changes to storage, and returning a confirmation message.
     *
     * @param tasks   The TaskList object that stores the tasks.
     * @param ui      The Ui object for user interaction (not directly used in this method).
     * @param storage The Storage object responsible for saving the task list.
     * @return A confirmation message indicating that the task list has been cleared.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        System.out.println("Okies, clearing list!");
        assert this.getTasks() != null : "TaskList object was not created properly";
        tasks.clear();
        storage.saveToFile(tasks);
        assert !this.getTasks().isEmpty() : "List not cleared properly";
        return "Okies, clearing list!";
    }
}
