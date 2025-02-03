package ekud.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ekud.parser.DateTimeParser;

public class Deadline extends Task {
    private final LocalDateTime due;
    private final String dueString;

    public Deadline(String task, String dueDate, int done) {
        super(task, done);
        if (DateTimeParser.parseDateTime(dueDate) != null) {
            this.due = DateTimeParser.parseDateTime(dueDate);
        } else if (DateTimeParser.parseDate(dueDate) != null) {
            this.due = DateTimeParser.parseDate(dueDate);
        } else {
            this.due = null;
        }
        this.dueString = dueDate;
        System.out.println(display());
    }

    public String display() {
        if (due == null) {
            return "[D][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName() + " (by: " + dueString + ")";
        } else {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return "[D][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName()
                    + " (by: " + due.format(outputFormat) + ")";
        }
    }

    public String getDue_string() {
        return this.dueString;
    }

    public LocalDateTime getDue() {
        return this.due;
    }
}
