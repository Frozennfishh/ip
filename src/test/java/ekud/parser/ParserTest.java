package ekud.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void isNotIntegerTest1() {
        assertEquals(false, Parser.isInteger("hi"));
    }

    @Test
    public void isNotIntegerTest2() {
        assertEquals(true, Parser.isInteger("1"));
    }

    @Test
    public void getDateTest() {
        assertEquals(LocalDate.of(2019, 12, 2),
                Parser.getDate("02 December 2019"));
    }

    @Test
    public void stringToMinutesTest() {
        assertEquals(45, Parser.stringToMinutes("45"));
    }

    @Test
    public void hourStringToMinutesTest() {
        assertEquals(120, Parser.hourStringToMinutes("2"));
    }
}
