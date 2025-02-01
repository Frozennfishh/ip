import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Ekud {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Ekud (String filePath) throws FileNotFoundException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
        ui = new Ui();
    }

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

    public static void main(String[] args) throws FileNotFoundException {
        new Ekud("data/list.txt").run();
    }
}
