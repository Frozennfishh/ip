package ekud.exceptions;

/**
 * Represents a custom exception for errors in the Ekud task manager.
 */
public class DukeException extends Exception{

    /**
     * Constructs a {@code DukeException} with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String e) {
        super(e);
    }
}
