package ekud.ui;

import java.util.ArrayList;
import java.util.Scanner;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Task;

/**
 * Handles user interaction for the Ekud task manager.
 * <p>
 * This class is responsible for reading user input, displaying messages,
 * and providing feedback based on user commands.
 * </p>
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a {@code Ui} object and initializes a scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of user input.
     *
     * @return The user's input as a string.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that the task list is empty.
     *
     * @return A message stating that the list is empty.
     */
    public String listEmpty() {
        System.out.println("List is empty! Yippee!");
        return "List is empty! Yippee!";
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
                /|
                (^.^7 \s
                |  |\s
                V__,)/
                """);
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________\n");
    }

    /**
     * Displays a goodbye message and terminates the program.
     *
     * @return A farewell message.
     */
    public String goodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        buffer();
        System.exit(0);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a visual separator for readability in the console.
     */
    public static void buffer() {
        System.out.println("   /\\_/\\\n"
                + "= (^ v ^) =\n"
                + "  /     \\     ");
        System.out.println("____________________________\n");
    }

    /**
     * Displays an error message when a task does not exist.
     *
     * @return An error message indicating that the task was not found.
     */
    public String taskDoesNotExist() {
        System.out.println("This task does not exist :( Try again!");
        return "This task does not exist :( Try again!";
    }

    /**
     * Displays an error message when no task input is provided.
     *
     * @return An error message prompting the user to enter a task.
     */
    public String taskNotGiven() {
        return "No task given, try again!";
    }

    /**
     * Marks a task as completed and displays a confirmation message.
     *
     * @param tasks The task list containing the task.
     * @param index The index of the task to be marked as completed.
     * @return A confirmation message indicating the task has been marked as done.
     */
    public String markDone(TaskList tasks, int index) {
        tasks.get(index).setDone();
        System.out.println("Yippee marking this task as done!");
        System.out.println(tasks.get(index).display());
        return "Yippee marking this task as done!\n"
                + tasks.get(index).display() + "\n"
                + tasks.leftCheck();
    }

    /**
     * Marks a task as not completed and displays a confirmation message.
     *
     * @param tasks The task list containing the task.
     * @param index The index of the task to be marked as not completed.
     * @return A confirmation message indicating the task has been marked as undone.
     */
    public String markUndone(TaskList tasks, int index) {
        tasks.get(index).setUndone();
        System.out.println("Awww marking this task undone :(");
        System.out.println(tasks.get(index).display());
        return "Awww marking this task undone :(\n"
                + tasks.get(index).display() + "\n"
                + tasks.leftCheck();
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param s The type of task that was added (e.g., "Todo", "Deadline", "Event").
     * @return A confirmation message.
     */
    public String taskAdded(String s) {
        System.out.println("Gotcha, " + s + " task added!");
        return "Gotcha, " + s + " task added!";
    }

    /**
     * Removes a task from the list and displays a confirmation message.
     *
     * @param tasks   The task list containing the task.
     * @param index   The index of the task to be deleted.
     * @param storage The storage instance to update the saved task list.
     * @return A confirmation message indicating the task has been deleted.
     */
    public String delete(TaskList tasks, int index, Storage storage) {
        System.out.println("Omgie, removing this task from the list!");
        System.out.println(tasks.get(index).display());
        String temp = tasks.get(index).display();
        tasks.remove(index, storage);
        return "Omgie, removing this task from the list! \n" + temp;
    }

    /**
     * Displays an error message for unrecognized commands.
     *
     * @return An error message indicating the command is unknown.
     */
    public String showUnknown() {
        return "I don't understand ;-; Try again!";
    }

    /**
     * Prints and returns a list of tasks that match a given search criteria.
     *
     * @param list A list of matching tasks.
     * @return A formatted string containing the matching tasks.
     */
    public String findTaskPrint(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("No related task found in this list :( Try again!");
            return "No related task found in this list :( Try again!";
        } else {
            StringBuilder sb = new StringBuilder();

            System.out.println("Here are the matching tasks in your list!");
            sb.append("Here are the matching tasks in your list!\n");

            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).display());
                sb.append(i + 1).append(". ").append(list.get(i).display()).append("\n");
            }

            return sb.toString();
        }
    }

    /**
     * Displays an error message when an invalid date is given.
     *
     * @return An error message indicating that the date is invalid.
     */
    public String invalidDateGiven() {
        return "Invalid date given, try again!";
    }
}
