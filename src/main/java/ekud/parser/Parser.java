package ekud.parser;

import java.time.LocalDate;
import java.util.Objects;
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

    /**
     * Checks whether the given string is a valid index within the provided {@code TaskList}.
     *
     * @param s The string to check.
     * @param t The {@code TaskList} where the index should exist.
     * @return {@code true} if the string is not an integer or exceeds the task list size, otherwise {@code false}.
     */
    public static boolean indexChecker(String s, TaskList t) {
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
        if(s.isEmpty()) return true;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return true;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return true;
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
