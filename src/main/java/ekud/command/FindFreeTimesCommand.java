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

public class FindFreeTimesCommand extends Command {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String hoursString;
    private String minuteString;
    private int minutes = 0;
    private LocalDateTime current;
    public FindFreeTimesCommand(String input) {
        super(input);
        System.out.println(input);
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
        System.out.println(hoursString);
        System.out.println(minuteString);
        System.out.println(minutes);
    }

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
            if (event.getStart().isBefore(startFreeTime)) {
                startFreeTime = event.getEnd();
                endFreeTime = null;
                continue;
            } else {
                endFreeTime = event.getStart();
                if (startFreeTime.until(endFreeTime.plusMinutes(1), ChronoUnit.MINUTES) >= minutes) {
                    System.out.println("End Time:" + endFreeTime);
                    break;
                } else {
                    startFreeTime = event.getEnd();
                    endFreeTime = null;
                }
            }
            System.out.println(startFreeTime);
            System.out.println(endFreeTime);
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
