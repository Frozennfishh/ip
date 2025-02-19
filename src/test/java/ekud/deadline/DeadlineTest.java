package ekud.deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ekud.tasks.Deadline;

public class DeadlineTest {
    Deadline deadlineNoDate = new Deadline("task1", "tomorrow", 0);
    Deadline deadlineWithDate = new Deadline("task2", "25/03/2025 1800", 0);

    @Test
    public void deadlineNoDateDisplayTest() {
        assertEquals("[D][ ] task1 (by: tomorrow)", deadlineNoDate.display());
    }

    @Test
    public void deadlineNoDateDoneTest() {
        deadlineNoDate.setDone();
        assertEquals("[D][X] task1 (by: tomorrow)", deadlineNoDate.display());
    }

    @Test
    public void deadlineNoDateGetDueStringTest() {
        assertEquals("tomorrow", deadlineNoDate.getDueString());
    }

    @Test
    public void deadlineNoDateGetDueTest() {
        assertEquals(null, deadlineNoDate.getDue());
    }

    @Test
    public void deadlineWithDateDisplayTest() {
        assertEquals("[D][ ] task2 (by: Mar 25 2025, 6:00 pm)", deadlineWithDate.display());
    }

    @Test
    public void deadlineWithDateDoneTest() {
        deadlineWithDate.setDone();
        assertEquals("[D][X] task2 (by: Mar 25 2025, 6:00 pm)", deadlineWithDate.display());
    }

    @Test
    public void deadlineWithDateGetDueStringTest() {
        assertEquals("25/03/2025 1800", deadlineWithDate.getDueString());
    }

    @Test
    public void deadlineWithDateGetDueTest() {
        assertEquals(LocalDateTime.of(2025, 3, 25, 18, 00),
                deadlineWithDate.getDue());
    }
}
