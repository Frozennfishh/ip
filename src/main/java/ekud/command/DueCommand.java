package ekud.command;

import java.time.LocalDate;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;


/**
 * Represents a command to check for tasks due on a specific date.
 */
public class DueCommand extends Command {
    private LocalDate dueDate;

    /**
     * Constructs a {@code DueCommand} with the given user input.
     * <p>
     * Parses the input to extract the due date.
     * </p>
     *
     * @param input The user input specifying the date to check for due tasks.
     */
    public DueCommand(String input) {
        super(input);
        this.dueDate = input == null ? null : Parser.getDate(this.getInput());
    }

    /**
     * Executes the command to check for tasks due on the specified date.
     * <p>
     * If the date is invalid or missing, an error message is returned.
     * Otherwise, it returns the list of tasks due on that date.
     * </p>
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance (not used in this method).
     * @return A response message showing the tasks due on the given date or an error message if the date is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        if (dueDate == null) {
            return ui.invalidDateGiven();
        }
        assert this.getTasks() != null : "TaskList object was not created properly";
        return this.getTasks().dueCheck(dueDate);
    }
}
