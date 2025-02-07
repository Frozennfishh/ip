import java.io.FileNotFoundException;

import ekud.gui.EkudApplication;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the file containing task data is not found.
     */
    public static void main(String[] args) {
        Application.launch(EkudApplication.class, args);
    }
}
