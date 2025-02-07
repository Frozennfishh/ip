package ekud.gui;

import java.io.FileNotFoundException;

import ekud.exceptions.DukeException;
import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.command.Command;
import ekud.parser.Parser;
import ekud.ui.Ui;

/**
 * The {@code ekud.gui.Ekud} class is the main entry point for the application.
 * It initializes the storage, task list, and user interface,
 * and handles the main program loop.
 */
public class Ekud {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an instance of {@code ekud.gui.Ekud}.
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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(taskList, ui, storage);
    }
}
