package ekud.ui;

import java.time.LocalDate;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Todo;

/**
 * Represents a user command in the Ekud task manager.
 * <p>
 * The {@code Command} class processes and executes different types of user commands,
 * including adding, marking, deleting, and listing tasks.
 * </p>
 */
public class Command {
    private boolean isExit = false;
    private String command;
    private String input;

    /**
     * Constructs a {@code Command} object from the user input.
     * <p>
     * The first element of the input array is treated as the command.
     * If there are additional elements, they are stored as the input string.
     * </p>
     *
     * @param input A string array containing the command and optional arguments.
     */
    public Command(String[] input) {
        this.command = input[0];
        this.input = input.length > 1 ? input[1] : null;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command) {
        case "bye": {
            isExit = true;
            break;
        }

        case "list": {
            if (tasks.isEmpty()) {
                ui.listEmpty();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).display());
                }
            }
            break;
        }

        case "clear": {
            System.out.println("Okies, clearing list!");
            tasks.clear();
            storage.saveToFile(tasks);
            break;
        }

        case "mark": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            if (Parser.indexChecker(input, tasks)) {
                ui.taskDoesNotExist();
            } else {
                ui.markDone(tasks, Integer.parseInt(input) - 1);
                storage.saveToFile(tasks);
            }
            break;
        }

        case "unmark": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            if (Parser.indexChecker(input, tasks)) {
                ui.taskDoesNotExist();
            } else {
                ui.markUndone(tasks, Integer.parseInt(input) - 1);
                storage.saveToFile(tasks);
            }
            break;
        }

        case "todo": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            ui.taskAdded("Todo");
            tasks.add(new Todo(input, 0), storage);
            break;
        }

        case "deadline": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            String[] temp = input.split(" /by ", 2);
            if (temp.length == 1) {
                System.out.println("Deadline not given! Try again!");
            } else {
                ui.taskAdded("Deadline");
                tasks.add(new Deadline(temp[0], temp[1], 0), storage);
            }
            break;
        }

        case "event": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            String[] temp = input.split(" /from ", 2);
            if (temp.length == 1) {
                System.out.println("Start time not given! Try again!");
            } else {
                String[] temp2 = temp[1].split(" /to ", 2);
                if (temp2.length == 1) {
                    System.out.println("End time not given! Try again!");
                } else {
                    ui.taskAdded("Event");
                    tasks.add(new Event(temp[0], temp2[0], temp2[1], 0), storage);
                }
            }
            break;
        }

        case "delete": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            if (Parser.indexChecker(input, tasks)) {
                ui.taskDoesNotExist();
            } else {
                ui.delete(tasks, Integer.parseInt(input) - 1, storage);
            }
            break;
        }

        case "due": {
            LocalDate dueDate = Parser.getDate(input);
            if (input == null || dueDate == null) {
                ui.taskNotGiven();
                break;
            }
            tasks.dueCheck(dueDate);
            break;
        }

        default: ui.unknown();

        }
        ui.buffer();
    }

    /**
     * Checks if the current command is an exit command.
     *
     * @return {@code true} if the command is "bye", otherwise {@code false}.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
