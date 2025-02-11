package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FindFreeTimesOnCommand extends Command {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDate date;
    private String hoursString;
    private String minuteString;
    private int minutes = 0;
    private LocalDateTime current;
    public FindFreeTimesOnCommand(String input) {
        super(input);
        //input in format "INPUT_DATE /for HH:MM"
        if (input != null) {
            String[] temp = input.split(" /for ", 2);
            this.date = Parser.getDate(temp[0]);
            String[] temp2 = temp.length > 1 ? temp[1].split(":", 2) : null;
            this.hoursString = temp2 == null || temp2.length <= 1 ? null : temp2[0];
            this.minuteString = temp2 != null && temp2.length > 1 ? temp2[1] : null;
            this.minutes += hoursString != null && Parser.isInteger(hoursString)
                    ? Integer.parseInt(hoursString) * 60 : 0;
            this.minutes += minuteString != null && Parser.isInteger(minuteString)
                    ? Integer.parseInt(minuteString) : 0;
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        return null;
    }
}
