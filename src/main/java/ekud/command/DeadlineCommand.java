package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Deadline;
import ekud.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String task;
    private String dueDate;

    /**
     * Constructs a {@code DeadlineCommand} with the given user input.
     * <p>
     * Parses the input to extract the task description and due date.
     * </p>
     *
     * @param input The user input containing the task description and due date.
     */
    public DeadlineCommand(String input) {
        super(input);
        if (this.getInput() != null) {
            String[] temp = input.split(" /by ", 2);
            this.task = temp[0];
            this.dueDate = temp.length > 1 ? temp[1] : null;
        }
    }

    /**
     * Executes the command to add a deadline task.
     * <p>
     * If no input is provided, it returns an error message. If the due date is missing,
     * it prompts the user to enter one. Otherwise, the deadline task is added to the task list.
     * </p>
     *
     * @param tasks   The task list where the deadline will be added.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance for saving the updated task list.
     * @return A response message indicating the outcome of the command execution.
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
        //No /by detected in input, therefore deadline not given
        if (dueDate == null) {
            System.out.println("Deadline not given! Try again!");
            return "Deadline not given! Try again!";
        }
        assert this.getTasks() != null : "TaskList object was not created properly";
        assert this.getStorage() != null : "Storage file does not exist";
        return ui.taskAdded("Deadline") + "\n"
                + this.getTasks().add(new Deadline(task, dueDate, 0), this.getStorage());
    }
}
