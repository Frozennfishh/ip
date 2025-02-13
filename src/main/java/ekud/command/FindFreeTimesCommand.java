package ekud.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.tasks.Event;
import ekud.ui.Ui;

/**
 * The FindFreeTimesCommand class searches for available time slots based on scheduled events.
 */
public class FindFreeTimesCommand extends Command {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String hoursString;
    private String minuteString;
    private int minutes = 0;
    private LocalDateTime current;

    /**
     * Constructs a FindFreeTimesCommand instance, parsing the input to determine the required free time duration.
     *
     * @param input The command input in the format "/for HH:MM" specifying the required free duration.
     */
    public FindFreeTimesCommand(String input) {
        super(input);
        //input in form "/for HH:MM"
        if (this.getInput() != null) {
            String[] temp = input.split("/for ", 2);
            String[] temp2 = temp.length > 1 ? temp[1].split(":", 2) : null;
            this.hoursString = temp2 == null || temp2.length <= 1 ? null : temp2[0];
            this.minuteString = temp2 != null && temp2.length > 1 ? temp2[1] : null;
            this.minutes += hoursString != null && Parser.isInteger(hoursString)
                    ? Integer.parseInt(hoursString) * 60 : 0;
            this.minutes += minuteString != null && Parser.isInteger(minuteString)
                    ? Integer.parseInt(minuteString) : 0;
        }
        current = LocalDateTime.now();
    }

    /**
     * Executes the command to find the next available free time slot.
     *
     * @param tasks   The TaskList containing scheduled events.
     * @param ui      The user interface instance for interacting with the user.
     * @param storage The storage system handling data persistence.
     * @return A message indicating the next available free time slot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        if (hoursString == null || !Parser.isInteger(hoursString)) {
            return "Time given is invalid, try again! (Format: HH:MM)";
        }
        if (minuteString == null || !Parser.isInteger(minuteString)) {
            return "Time given is invalid, try again! (Format: HH:MM)";
        }
        ArrayList<Event> eventList = tasks.getAllEvents();
        eventList.sort(Comparator.comparing(Event::getStart));
        LocalDateTime startFreeTime = current;
        LocalDateTime endFreeTime = null;
        for (Event event : eventList) {
            //If event has been marked done, move to next
            if (event.getDone() == 1) {
                continue;
            }
            //If event start time is before the end time of previous event,
            //Move startFreeTime to the end of current event, move to next
            if (event.getStart().isBefore(startFreeTime)) {
                startFreeTime = event.getEnd();
                endFreeTime = null;
                continue;
            }
            endFreeTime = event.getStart();
            if (startFreeTime.until(endFreeTime.plusMinutes(1), ChronoUnit.MINUTES) >= minutes) {
                System.out.println("End Time:" + endFreeTime);
                break;
            } else {
                startFreeTime = event.getEnd();
                endFreeTime = null;
            }
        }

        if (startFreeTime == current && endFreeTime == null) {
            return "You're free all the way to infinity!";
        } else if (endFreeTime == null) {
            return "You will only be free after " + startFreeTime.format(dateTimeFormatter);
        } else {
            return "You will be free from " + startFreeTime.format(dateTimeFormatter)
                    + " to " + endFreeTime.format(dateTimeFormatter);
        }
    }
}
