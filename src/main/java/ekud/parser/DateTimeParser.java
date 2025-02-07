package ekud.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing date and date-time strings into {@code LocalDateTime} or {@code LocalDate} objects.
 * <p>
 * Supports multiple date and date-time formats to handle various input styles.
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
     * Attempts to match the input string against multiple predefined date-time formats.
     * If parsing succeeds, a corresponding {@code LocalDateTime} object is returned.
     * Otherwise, returns {@code null}.
     * </p>
     *
     * @param input The date-time string to parse.
     * @return A {@code LocalDateTime} object if parsing is successful, otherwise {@code null}.
     */
    public static LocalDateTime parseDateTime(String input) {
        for (String pattern : DATE_TIME_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                @SuppressWarnings("unused")
                Exception ignored = e;
            }
        }
        return null;
    }

    /**
     * Parses a date string into a {@code LocalDateTime} object with time set to midnight.
     * <p>
     * Attempts to match the input string against multiple predefined date formats.
     * If parsing succeeds, the resulting {@code LocalDate} is converted to a {@code LocalDateTime}
     * by setting the time to midnight (00:00).
     * If parsing fails, returns {@code null}.
     * </p>
     *
     * @param input The date string to parse.
     * @return A {@code LocalDateTime} object with time set to midnight if parsing is successful,
     *         otherwise {@code null}.
     */
    public static LocalDateTime parseDate(String input) {
        for (String pattern : DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(input, formatter).atTime(LocalTime.MIDNIGHT);
            } catch (DateTimeParseException e) {
                @SuppressWarnings("unused")
                Exception ignored = e;
            }
        }
        return null;
    }
}
