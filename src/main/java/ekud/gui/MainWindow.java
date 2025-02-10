package ekud.gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Ekud application.
 * <p>
 * This class manages user input and responses, displaying them in a dialog container.
 * It initializes the UI components and handles user interactions.
 * </p>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ekud ekud;

    private Image userImage = new Image(Objects
            .requireNonNull(this.getClass().getResourceAsStream("/images/cat_right.png")));
    private Image ekudImage = new Image(Objects
            .requireNonNull(this.getClass().getResourceAsStream("/images/cat_left.png")));

    /**
     * Initializes the GUI components.
     * <p>
     * Binds the scroll pane to automatically scroll down as new messages are added to the dialog container.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@code Ekud} instance into the controller.
     *
     * @param d The {@code Ekud} instance that handles user commands.
     */
    public void setEkud(Ekud d) {
        ekud = d;
    }

    /**
     * Handles user input.
     * <p>
     * Creates two dialog boxes: one displaying the user's input and the other displaying Ekud's response.
     * The user input field is cleared after processing.
     * </p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ekud.getResponse(input);
        String commandType = ekud.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEkudDialog(response, ekudImage, commandType)
        );
        userInput.clear();
    }
}
