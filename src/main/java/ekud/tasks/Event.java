package ekud.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ekud.parser.DateTimeParser;

public class Event extends Task {
    private final String startString;
    private final String endString;

    private final LocalDateTime start;
    private final LocalDateTime end;

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

    public String display() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String s = start != null ? start.format(outputFormat) : this.startString;
        String e = end != null ? end.format(outputFormat) : this.endString;
        return "[E][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName()
                + " (from: " + s + " to: " + e + ")";
    }

    public String getStart_string() {
        return this.startString;
    }

    public String getEnd_string() {
        return this.endString;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
