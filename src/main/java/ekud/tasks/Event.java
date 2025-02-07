package ekud.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ekud.parser.DateTimeParser;

/**
 * Represents an event task with a start and end time.
 * <p>
 * The {@code Event} class extends {@code Task} and includes start and end date-time information.
 * It supports multiple date-time formats and displays formatted event details.
 * </p>
 */
public class Event extends Task {
    private final String startString;
    private final String endString;

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an {@code Event} task with a specified name, start time, end time, and completion status.
     * <p>
     * The constructor attempts to parse the start and end times using {@code DateTimeParser}.
     * If a valid format is found, it is stored as a {@code LocalDateTime}.
     * Otherwise, the original string input is retained.
     * </p>
     *
     * @param name  The name of the event.
     * @param start The start date and time as a string.
     * @param end   The end date and time as a string.
     * @param done  The completion status of the event (1 for done, 0 for not done).
     */
    public Event(String name, String start, String end, int done) {
        super(name, done);
        this.startString = start;
        this.endString = end;
        if (DateTimeParser.parseDateTime(start) != null) {
            this.start = DateTimeParser.parseDateTime(start);
        } else if (DateTimeParser.parseDate(start) != null) {
            this.start = DateTimeParser.parseDate(start);
        } else {
            this.start = null;
        }
        if (DateTimeParser.parseDateTime(end) != null) {
            this.end = DateTimeParser.parseDateTime(end);
        } else if (DateTimeParser.parseDate(end) != null) {
            this.end = DateTimeParser.parseDate(end);
        } else {
            this.end = null;
        }
        System.out.println(display());
    }

    /**
     * Returns a formatted string representation of the event.
     * <p>
     * If the start or end time was successfully parsed, it is formatted in
     * {@code "MMM dd yyyy, h:mm a"} format.
     * Otherwise, the original input string is displayed.
     * </p>
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String display() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String s = start != null ? start.format(outputFormat) : this.startString;
        String e = end != null ? end.format(outputFormat) : this.endString;
        return "[E][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName()
                + " (from: " + s + " to: " + e + ")";
    }

    /**
     * Retrieves the original start time string entered by the user.
     *
     * @return The raw start time string.
     */
    public String getStart_string() {
        return this.startString;
    }

    /**
     * Retrieves the original end time string entered by the user.
     *
     * @return The raw end time string.
     */
    public String getEnd_string() {
        return this.endString;
    }

    /**
     * Retrieves the parsed end time as a {@code LocalDateTime} object.
     *
     * @return The end time as a {@code LocalDateTime}, or {@code null} if parsing failed.
     */
    public LocalDateTime getEnd() {
        return this.end;
    }
}
