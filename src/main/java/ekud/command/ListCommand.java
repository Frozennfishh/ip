package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a {@code ListCommand} with the given user input.
     *
     * @param input The user input (not used in this command).
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Executes the list command.
     * <p>
     * If the task list is empty, it returns an appropriate message. Otherwise, it constructs
     * and returns a formatted list of all tasks.
     * </p>
     *
     * @param tasks   The task list to retrieve tasks from.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance (not used in this command).
     * @return A formatted string listing all tasks or a message if the list is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return ui.listEmpty();
        } else {
            StringBuilder sb = new StringBuilder();

            System.out.println("Here are the tasks in your list:");
            sb.append("Here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).display());
                sb.append(i + 1).append(". ").append(tasks.get(i).display()).append("\n");
            }

            return sb.toString();
        }
    }
}
