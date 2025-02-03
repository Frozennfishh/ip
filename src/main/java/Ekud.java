import java.io.FileNotFoundException;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Command;
import ekud.ui.Ui;

/**
 * The {@code Ekud} class is the main entry point for the application.
 * It initializes the storage, task list, and user interface,
 * and handles the main program loop.
 */
public class Ekud {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an instance of {@code Ekud}.
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws FileNotFoundException If the file specified by {@code filePath} is not found.
     */
    public Ekud(String filePath) throws FileNotFoundException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
        ui = new Ui();
    }


    /**
     * Runs the main program loop.
     * - Displays the introduction message.
     * - Reads user input and executes commands.
     * - Terminates when the exit command is issued.
     */
    public void run() {
        ui.intro();
        boolean isExit = false;
        while (!isExit) {
            Command c = new Command(ui.readLine());
            c.execute(taskList, ui, storage);
            isExit = c.isExit();
        }
        ui.goodbye();
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the file containing task data is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Ekud("data/list.txt").run();
    }
}
