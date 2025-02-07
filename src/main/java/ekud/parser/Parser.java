package ekud.parser;

import java.time.LocalDate;
import java.util.Objects;

import ekud.command.ClearCommand;
import ekud.command.Command;
import ekud.command.DeadlineCommand;
import ekud.command.DeleteCommand;
import ekud.command.DueCommand;
import ekud.command.EventCommand;
import ekud.command.ExitCommand;
import ekud.command.ListCommand;
import ekud.command.MarkCommand;
import ekud.command.TodoCommand;
import ekud.command.UnknownCommand;
import ekud.command.UnmarkCommand;
import ekud.memory.TaskList;

/**
 * The {@code Parser} class provides utility methods for parsing user input
 * and validating index values within a task list.
 */
public class Parser {

    /**
     * Constructs a {@code Parser} object.
     */
    public Parser() {
    }

    public static Command parse(String s) {
        String[] temp = s.split(" ", 2);
        String command = temp[0];
        String input = temp.length > 1 ? temp[1] : null;
        switch (command) {
        case "bye": return new ExitCommand(input);
        case "list": return new ListCommand(input);
        case "clear": return new ClearCommand(input);
        case "mark": return new MarkCommand(input);
        case "unmark": return new UnmarkCommand(input);
        case "todo": return new TodoCommand(input);
        case "deadline": return new DeadlineCommand(input);
        case "event": return new EventCommand(input);
        case "delete": return new DeleteCommand(input);
        case "due": return new DueCommand(input);
        default: return new UnknownCommand(input);
        }
    }

    /**
     * Checks whether the given string is a valid index within the provided {@code TaskList}.
     *
     * @param s The string to check.
     * @param t The {@code TaskList} where the index should exist.
     * @return {@code true} if the string is not an integer or exceeds the task list size, otherwise {@code false}.
     */
    public static boolean isValidIndex(String s, TaskList t) {
        return isNotInteger(s, 10) || Integer.parseInt(s) > t.size();
    }

    /**
     * Determines whether a given string is not a valid integer in the specified radix.
     *
     * @param s The string to check.
     * @param radix The numerical base (e.g., 10 for decimal).
     * @return {@code true} if the string is not a valid integer, otherwise {@code false}.
     */
    public static boolean isNotInteger(String s, int radix) {
        if (s.isEmpty()) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return true;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses a date string and converts it into a {@code LocalDate} object.
     * <p>
     * This method attempts to parse the input using predefined date-time patterns.
     * If parsing as a {@code LocalDateTime} succeeds, it extracts the date portion.
     * If parsing as a {@code LocalDate} succeeds, it returns the corresponding date.
     * If both attempts fail, {@code null} is returned.
     * </p>
     *
     * @param input The date string to parse.
     * @return A {@code LocalDate} object if parsing is successful, otherwise {@code null}.
     */
    public static LocalDate getDate(String input) {
        if (DateTimeParser.parseDateTime(input) != null) {
            return Objects.requireNonNull(DateTimeParser.parseDateTime(input)).toLocalDate();
        } else if (DateTimeParser.parseDate(input) != null) {
            return Objects.requireNonNull(DateTimeParser.parseDate(input)).toLocalDate();
        } else {
            return null;
        }
    }
}
