package ekud.tasks;

import ekud.parser.DateTimeParser;
import ekud.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final String start_string;
    private final String end_string;

    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String name, String start, String end, int done) {
        super(name, done);
        this.start_string = start;
        this.end_string = end;
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

    public String display() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String s = start != null ? start.format(outputFormat) : this.start_string;
        String e = end != null ? end.format(outputFormat) : this.end_string;
        return "[E][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName() +
                " (from: " + s + " to: " + e + ")";
    }

    public String getStart_string() {
        return this.start_string;
    }

    public String getEnd_string() {
        return this.end_string;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
