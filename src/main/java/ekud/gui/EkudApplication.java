package ekud.gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Ekud GUI application.
 * <p>
 * This class initializes the JavaFX application, loads the main UI layout,
 * and injects an instance of {@code Ekud} into the controller.
 * </p>
 */
public class EkudApplication extends Application {
    private final Ekud ekud = new Ekud("data/list.txt");

    /**
     * Constructs an instance of {@code EkudApplication}.
     *
     * @throws FileNotFoundException If the task data file is not found.
     */
    public EkudApplication() throws FileNotFoundException {
    }

    /**
     * Starts the JavaFX application.
     * <p>
     * Loads the main window layout from an FXML file, initializes the scene,
     * sets the minimum window size, and injects the {@code Ekud} instance
     * into the controller.
     * </p>
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(EkudApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setEkud(ekud);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
