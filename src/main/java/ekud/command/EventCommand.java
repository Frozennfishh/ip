package ekud.command;

import java.util.ArrayList;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Event;
import ekud.tasks.Task;
import ekud.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private String task;
    private String startDate;
    private String endDate;

    /**
     * Constructs an {@code EventCommand} with the given user input.
     * <p>
     * Parses the input to extract the task description, start date, and end date.
     * </p>
     *
     * @param input The user input specifying the event details.
     */
    public EventCommand(String input) {
        super(input);
        if (this.getInput() != null) {
            String[] temp = input.split(" /from ", 2);
            this.task = temp[0];
            String[] temp2 = temp.length > 1 ? temp[1].split(" /to ", 2) : null;
            this.startDate = temp2 == null || temp2.length <= 1 ? null : temp2[0];
            this.endDate = temp2 != null && temp2.length > 1 ? temp2[1] : null;
        }
    }

    /**
     * Executes the command to add an event task.
     * <p>
     * If any required field (task, start date, or end date) is missing, an error message is returned.
     * Otherwise, the event task is added to the task list.
     * </p>
     *
     * @param tasks   The task list where the event will be added.
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
        //no input between /to and /from in input
        if (startDate == null) {
            System.out.println("Start date not given");
            return "Start date not given";
        }
        //no input after /from input
        if (endDate == null) {
            System.out.println("End date not given");
            return "End date not given";
        }
        assert this.getTasks() != null : "TaskList object was not created properly";
        assert this.getStorage() != null : "Storage file does not exist";
        return ui.taskAdded("Event") + "\n"
                + this.getTasks().add(new Event(task, startDate, endDate, 0), this.getStorage());
    }
}
