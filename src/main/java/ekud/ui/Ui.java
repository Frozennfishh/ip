package ekud.ui;

import ekud.memory.Storage;
import ekud.memory.TaskList;

import java.util.Scanner;

/**
 * Handles user interaction for the Ekud task manager.
 * <p>
 * The {@code Ui} class is responsible for reading user input, displaying messages,
 * and providing feedback based on user commands.
 * </p>
 */
public class Ui {
    Scanner scanner;

    /**
     * Constructs a {@code Ui} object and initializes a {@code Scanner} for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of user input and splits it into a command and optional arguments.
     *
     * @return A string array where the first element is the command and the second (if present) is the argument.
     */
    public String[] readLine() {
        return scanner.nextLine().split(" ", 2);
    }

    /**
     * Displays a message indicating that the task list is empty.
     */
    public void listEmpty() {
        System.out.println("List is empty! Yippee!");
    }

    /**
     * Displays an introduction message when the program starts.
     */
    public void intro() {
        String logo = """

                EEEEE K  KK U   U DDD   !
                E     KKK   U   U D  D  !
                EEEEE K     U   U D   D !
                E     KKK   UU UU D  D  \s
                EEEEE K  KK  UUU  DDD   !""";
        System.out.println("____________________________\n");
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("""
                ╱|
                (^ˎ ^7 \s
                |  〵         \s
                V__,)/
                """);
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________\n");
    }

    /**
     * Displays a goodbye message and exits the program.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        buffer();
        System.exit(0);
    }

    /**
     * Displays a visual separator to improve readability in the console.
     */
    public void buffer() {
        System.out.println("ฅ^•ﻌ•^ฅ");
        System.out.println("____________________________\n");
    }

    /**
     * Displays an error message when a task does not exist.
     */
    public void taskDoesNotExist() {
        System.out.println("This task does not exist :( Try again!");
    }

    /**
     * Displays an error message when no task input is provided.
     */
    public void taskNotGiven() {
        System.out.println("No task given, try again!");
    }

    /**
     * Marks a task as completed, displays a confirmation message, and updates the task list.
     *
     * @param tasks The {@code TaskList} containing the task.
     * @param index The index of the task to mark as completed.
     */
    public void markDone(TaskList tasks, int index) {
        tasks.get(index).setDone();
        System.out.println("Yippee marking this task as done!");
        System.out.println(tasks.get(index).display());
        tasks.leftCheck();
    }

    /**
     * Marks a task as not completed, displays a confirmation message, and updates the task list.
     *
     * @param tasks The {@code TaskList} containing the task.
     * @param index The index of the task to mark as not completed.
     */
    public void markUndone(TaskList tasks, int index) {
        tasks.get(index).setUndone();
        System.out.println("Awww marking this task undone :(");
        System.out.println(tasks.get(index).display());
        tasks.leftCheck();
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param s The type of task that was added (e.g., "Todo", "Deadline", "Event").
     */
    public void taskAdded(String s) {
        System.out.println("Gotcha, " + s + " task added!");
    }

    /**
     * Removes a task from the list and displays a confirmation message.
     *
     * @param tasks   The {@code TaskList} containing the task.
     * @param index   The index of the task to be deleted.
     * @param storage The {@code Storage} instance to update the saved task list.
     */
    public void delete(TaskList tasks, int index, Storage storage) {
        System.out.println("Omgie, removing this task from the list!");
        System.out.println(tasks.get(index).display());
        tasks.remove(index, storage);
    }

    /**
     * Displays an error message for unrecognized commands.
     */
    public void unknown() {
        System.out.println("I don't understand ;-; Try again!");
    }
}
