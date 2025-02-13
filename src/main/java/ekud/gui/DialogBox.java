package ekud.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box used in the Ekud GUI.
 * <p>
 * This class is responsible for displaying dialog messages along with a user or system image.
 * It supports flipping the layout to differentiate between user and system responses.
 * </p>
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and image.
     * <p>
     * Loads the corresponding FXML layout and initializes the dialog text and image.
     * </p>
     *
     * @param text The text content of the dialog box.
     * @param img  The image to be displayed in the dialog box.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the {@code ImageView} appears on the left
     * and the text appears on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box representing the user's message.
     *
     * @param text The text content of the user message.
     * @param img  The image representing the user.
     * @return A {@code DialogBox} containing the user’s message and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }


    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "ClearCommand":
            dialog.getStyleClass().add("clear-label");
            break;
        case "DeadlineCommand", "EventCommand", "TodoCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "DueCommand", "FindCommand", "ListCommand", "FindFreeTimesCommand", "FindFreeTimesOnCommand":
            dialog.getStyleClass().add("list-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("mark-label");
            break;
        case "UnknownCommand":
            dialog.getStyleClass().add("unknown-label");
            break;
        case "UnmarkCommand":
            dialog.getStyleClass().add("unmark-label");
            break;
        case "ExitCommand":
            break;
        default:
            assert false : commandType;
        }
    }

    /**
     * Creates a dialog box representing Ekud's response.
     * <p>
     * The layout is flipped to distinguish system responses from user messages.
     * </p>
     *
     * @param text The text content of Ekud's response.
     * @param img  The image representing Ekud.
     * @return A {@code DialogBox} containing Ekud’s response and image.
     */
    public static DialogBox getEkudDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
