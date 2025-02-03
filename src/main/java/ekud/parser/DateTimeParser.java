package ekud.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code DateTimeParser} class provides methods for parsing date and time strings
 * into {@code LocalDateTime} or {@code LocalDate} objects.
 * <p>
 * It supports multiple date and date-time formats to accommodate various input formats.
 * </p>
 */
public class DateTimeParser {
    private static final String[] DATE_TIME_PATTERNS = {
            "d/M/yyyy HHmm",
            "dd/MM/yyyy HH:mm",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm",
            "dd MMM yyyy HH:mm",
            "dd MMMM yyyy HH:mm",
            "EEE, dd MMM yyyy HH:mm",
            "EEEE, dd MMMM yyyy HH:mm"
    };

    private static final String[] DATE_PATTERNS = {
            "d/M/yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd MMM yyyy",
            "dd MMMM yyyy",
            "EEE, dd MMM yyyy",
            "EEEE, dd MMMM yyyy"
    };

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     * <p>
     * The method iterates through an array of predefined date-time patterns.
     * If a pattern matches, the corresponding {@code LocalDateTime} object is returned.
     * If no patterns match, {@code null} is returned.
     * </p>
     *
     * @param input The date-time string to be parsed.
     * @return A {@code LocalDateTime} object if parsing succeeds, otherwise {@code null}.
     */
    public static LocalDateTime parseDateTime(String input) {
        for (String pattern : DATE_TIME_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        return null;
    }

    /**
     * Parses a date string into a {@code LocalDateTime} object.
     * <p>
     * The method iterates through an array of predefined date patterns.
     * If a pattern matches, the corresponding {@code LocalDate} object is created and
     * then converted into {@code LocalDateTime} by setting the time to midnight.
     * If no patterns match, {@code null} is returned.
     * </p>
     *
     * @param input The date string to be parsed.
     * @return A {@code LocalDateTime} object with time set to midnight if parsing succeeds, otherwise {@code null}.
     */
    public static LocalDateTime parseDate(String input) {
        for (String pattern : DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(input, formatter).atTime(LocalTime.MIDNIGHT);
            } catch (DateTimeParseException ignored) {
            }
        }
        return null;
    }
}
