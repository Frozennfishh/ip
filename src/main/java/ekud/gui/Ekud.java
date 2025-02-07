package ekud.gui;

import java.io.FileNotFoundException;

import ekud.command.Command;
import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

/**
 * The {@code Ekud} class serves as the main controller for the application.
 * <p>
 * It initializes the storage, task list, and user interface, and handles
 * user input processing by parsing and executing commands.
 * </p>
 */
public class Ekud {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an instance of {@code Ekud}.
     * <p>
     * Initializes the storage, task list, and UI components. Loads existing
     * tasks from the specified file if available.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public Ekud(String filePath) throws FileNotFoundException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
        ui = new Ui();
    }


    /**
     * Runs the main program logic.
     * <p>
     * Displays the introduction message, continuously reads user input,
     * processes commands, and terminates when the exit command is issued.
     * </p>
     *
     * @return A farewell message when the program terminates.
     */
    public String run() {
        ui.intro();
        boolean isExit = false;
        while (!isExit) {
            Command c = Parser.parse(ui.readLine());
            return c.execute(taskList, ui, storage);
        }
        return ui.goodbye();
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

    /**
     * Generates a response for the user's chat input.
     * <p>
     * Parses the input and executes the corresponding command.
     * </p>
     *
     * @param input The user's chat message.
     * @return The response generated by executing the command.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(taskList, ui, storage);
    }
}
