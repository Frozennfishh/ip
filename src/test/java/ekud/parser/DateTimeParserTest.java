package ekud.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class DateTimeParserTest {
    //Arbituary numbers used for naming due to the inputs being the same, just in different format
    @Test
    public void parseDateTimeTest1() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("2/12/2019 1800"));
    }

    @Test
    public void parseDateTimeTest2() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("02/12/2019 18:00"));
    }

    @Test
    public void parseDateTimeTest3() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("2019-12-02 18:00:00"));
    }

    @Test
    public void parseDateTimeTest4() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("2019/12/02 18:00"));
    }

    @Test
    public void parseDateTimeTest5() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("02 Dec 2019 18:00"));
    }

    @Test
    public void parseDateTimeTest6() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("02 December 2019 18:00"));
    }

    @Test
    public void parseDateTimeTest7() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00), DateTimeParser.parseDateTime("Mon, 02 Dec 2019 18:00"));
    }

    @Test
    public void parseDateTimeTest8() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 00),
                DateTimeParser.parseDateTime("Monday, 02 December 2019 18:00"));
    }

    @Test
    public void parseDateTest1() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("2/12/2019"));
    }

    @Test
    public void parseDateTest2() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("02/12/2019"));
    }

    @Test
    public void parseDateTest3() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("2019-12-02"));
    }

    @Test
    public void parseDateTest4() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("2019/12/02"));
    }

    @Test
    public void parseDateTest5() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("02 Dec 2019"));
    }

    @Test
    public void parseDateTest6() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("02 December 2019"));
    }

    @Test
    public void parseDateTest7() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("Mon, 02 Dec 2019"));
    }

    @Test
    public void parseDateTest8() {
        assertEquals(LocalDateTime.of(2019, 12, 2, 00, 00), DateTimeParser.parseDate("Monday, 02 December 2019"));
    }
}
