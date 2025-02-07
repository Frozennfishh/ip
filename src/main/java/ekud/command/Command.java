package ekud.command;

import java.time.LocalDate;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Todo;
import ekud.ui.Ui;

/**
 * Represents a user command in the ekud.gui.Ekud task manager.
 * <p>
 * The {@code Command} class processes and executes different types of user commands,
 * including adding, marking, deleting, and listing tasks.
 * </p>
 */
public class Command {
    private String input;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a {@code Command} object from the user input.
     * <p>
     * The first element of the input array is treated as the command.
     * If there are additional elements, they are stored as the input string.
     * </p>
     *
     * @param input A string array containing the command and optional arguments.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command based on its type.
     * <p>
     * The method processes various commands, such as:
     * <ul>
     *     <li>{@code bye} - Exits the program.</li>
     *     <li>{@code list} - Displays all tasks.</li>
     *     <li>{@code clear} - Clears all tasks from the list.</li>
     *     <li>{@code mark} - Marks a task as completed.</li>
     *     <li>{@code unmark} - Unmarks a completed task.</li>
     *     <li>{@code todo} - Adds a new to-do task.</li>
     *     <li>{@code deadline} - Adds a deadline task with a due date.</li>
     *     <li>{@code event} - Adds an event task with a start and end date.</li>
     *     <li>{@code delete} - Removes a task from the list.</li>
     *     <li>{@code due} - Displays tasks due on a specific date.</li>
     *     <li>Other inputs are treated as unknown commands.</li>
     * </ul>
     * </p>
     *
     * @param tasks   The {@code TaskList} where tasks are stored.
     * @param ui      The {@code Ui} instance for user interaction.
     * @param storage The {@code Storage} instance for saving tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        return null;
    }

    public String getInput() {
        return this.input;
    }

    public String getString() {
        return null;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }
}
