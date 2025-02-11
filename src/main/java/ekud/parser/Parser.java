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
import ekud.command.FindCommand;
import ekud.command.FindFreeTimesCommand;
import ekud.command.ListCommand;
import ekud.command.MarkCommand;
import ekud.command.TodoCommand;
import ekud.command.UnknownCommand;
import ekud.command.UnmarkCommand;
import ekud.memory.TaskList;

/**
 * Provides utility methods for parsing user input and validating index values within a task list.
 */
public class Parser {

    /**
     * Constructs a {@code Parser} object.
     */
    public Parser() {
    }

    /**
     * Parses a user input string and returns the corresponding {@code Command} object.
     * <p>
     * The method extracts the command keyword from the input and maps it to the appropriate command class.
     * If the command is unrecognized, an {@code UnknownCommand} is returned.
     * </p>
     *
     * @param s The user input string.
     * @return The corresponding {@code Command} object based on the input.
     */
    public static Command parse(String s) {
        String[] temp = s.split(" ", 2);
        String command = temp[0];
        String input = temp.length > 1 ? temp[1] : null;
        return switch (command) {
        case "bye" -> new ExitCommand(input);
        case "list" -> new ListCommand(input);
        case "clear" -> new ClearCommand(input);
        case "mark" -> new MarkCommand(input);
        case "unmark" -> new UnmarkCommand(input);
        case "todo" -> new TodoCommand(input);
        case "deadline" -> new DeadlineCommand(input);
        case "event" -> new EventCommand(input);
        case "delete" -> new DeleteCommand(input);
        case "due" -> new DueCommand(input);
        case "find" -> new FindCommand(input);
        case "free" -> new FindFreeTimesCommand(input);
        default -> new UnknownCommand(input);
        };
    }

    /**
     * Checks whether the given string is a valid index within the provided {@code TaskList}.
     * <p>
     * A valid index must be an integer within the bounds of the task list size.
     * </p>
     *
     * @param s The string representing the index.
     * @param t The {@code TaskList} where the index should exist.
     * @return {@code true} if the string is not an integer or exceeds the task list size, otherwise {@code false}.
     */
    public static boolean isValidIndex(String s, TaskList t) {
        assert !t.isEmpty() : "Task List not given";
        return isInteger(s) && Integer.parseInt(s) <= t.size();
    }

    /**
     * Checks if a given string can be parsed as an integer.
     * <p>
     * This method attempts to parse the string using {@code Integer.parseInt()}.
     * If parsing succeeds, it returns {@code true}; otherwise, it catches
     * a {@code NumberFormatException} and returns {@code false}.
     * </p>
     *
     * @param str The string to check.
     * @return {@code true} if the string can be parsed as an integer, otherwise {@code false}.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Parses a date string and converts it into a {@code LocalDate} object.
     * <p>
     * This method attempts to parse the input using predefined date-time formats.
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
