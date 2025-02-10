package ekud.command;


import java.util.Arrays;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

public class FindFreeTimesCommand extends Command {
    private Integer hours;
    private Integer minutes;
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
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        return "ok";
    }
}
