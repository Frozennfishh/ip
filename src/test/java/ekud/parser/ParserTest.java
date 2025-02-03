package ekud.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void isNotIntegerTest1() {
        assertEquals(true, Parser.isNotInteger("hi",10));
    }

    @Test
    public void isNotIntegerTest2() {
        assertEquals(false, Parser.isNotInteger("1",10));
    }

    @Test
    public void getDateTest() {
        assertEquals(LocalDate.of(2019,12,2), Parser.getDate("02 December 2019"));
    }
}
