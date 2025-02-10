package ekud.command;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.tasks.Event;
import ekud.tasks.Task;
import ekud.ui.Ui;

public class FindFreeTimesCommand extends Command {
    private Integer hours;
    private Integer minutes;
    private LocalDateTime current;
    public FindFreeTimesCommand(String input) {
        super(input);
        System.out.println(input);
        if (this.getInput() != null) {
            String[] temp = input.split("/for ", 2);
            String[] temp2 = temp.length > 1 ? temp[1].split(":", 2) : null;
            String hoursString = temp2 == null || temp2.length <= 1 ? null : temp2[0];
            String minuteString = temp2 != null && temp2.length > 1 ? temp2[1] : null;
            this.hours = hoursString != null && Parser.isInteger(hoursString)
                    ? Integer.parseInt(hoursString) : null;
            this.minutes = minuteString != null && Parser.isInteger(minuteString)
                    ? Integer.parseInt(minuteString) : null;
        }
        current = LocalDateTime.now();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        if (hours == null) {
            return "Hours given is invalid, try again! (Format: HH:MM)";
        }
        if (minutes == null) {
            return "Minutes given is invalid, try again! (Format: HH:MM)";
        }
        ArrayList<Event> eventList = tasks.getAllEvents();
        eventList.sort(Comparator.comparing(Event::getStart));
        return null;
    }
}
