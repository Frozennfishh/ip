import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime due;
    private final String due_string;

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

    public String display() {
        if (due == null) {
            return "[D][" + (done == 1 ? "X" : " ") + "] " + name + " (by: " + due_string + ")";
        } else {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return "[D][" + (done == 1 ? "X" : " ") + "] " + name + " (by: " + due.format(outputFormat) + ")";
        }
    }

    public String getDue_string() {
        return this.due_string;
    }

    public LocalDateTime getDue() {
        return this.due;
    }
}
