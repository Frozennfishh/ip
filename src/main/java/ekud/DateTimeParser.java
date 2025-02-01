package ekud;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
