package ekud.tasks;

import ekud.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * <p>
 * The {@code Deadline} class extends {@code Task} and includes a due date.
 * It supports different date-time formats for parsing and displays the formatted deadline.
 * </p>
 */
public class Deadline extends Task {
    private final LocalDateTime due;
    private final String due_string;

    /**
     * Constructs a {@code Deadline} task with a specified name, deadline date, and completion status.
     * <p>
     * The constructor attempts to parse the deadline string into a {@code LocalDateTime}.
     * If a valid date-time format is found, it is stored in {@code due}.
     * Otherwise, the original string is retained.
     * </p>
     *
     * @param task    The name of the deadline task.
     * @param dueDate The due date as a string.
     * @param done    The completion status of the task (1 for done, 0 for not done).
     */
    public Deadline(String task, String dueDate, int done) {
        super(task, done);
        if (DateTimeParser.parseDateTime(dueDate) != null) {
            this.due = DateTimeParser.parseDateTime(dueDate);
        } else if (DateTimeParser.parseDate(dueDate) != null) {
            this.due = DateTimeParser.parseDate(dueDate);
        } else {
            this.due = null;
        }
        this.due_string = dueDate;
        System.out.println(display());
    }

    @Override
    /**
     * Returns the string representation of the deadline task.
     * <p>
     * If the due date was successfully parsed, it is formatted in {@code "MMM dd yyyy, h:mm a"} format.
     * Otherwise, the original due date string is displayed.
     * </p>
     *
     * @return A formatted string representing the deadline task.
     */
    public String display() {
        if (due == null) {
            return "[D][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName() + " (by: " + due_string + ")";
        } else {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return "[D][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName() + " (by: " + due.format(outputFormat) + ")";
        }
    }

    /**
     * Retrieves the original due date string entered by the user.
     *
     * @return The raw due date string.
     */
    public String getDue_string() {
        return this.due_string;
    }

    /**
     * Retrieves the parsed due date as a {@code LocalDateTime} object.
     *
     * @return The due date as a {@code LocalDateTime}, or {@code null} if parsing failed.
     */
    public LocalDateTime getDue() {
        return this.due;
    }
}
