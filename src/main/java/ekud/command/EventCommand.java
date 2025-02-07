package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Event;
import ekud.ui.Ui;

public class EventCommand extends Command{
    String task;
    String startDate;
    String endDate;
    public EventCommand(String input) {
        super(input);
        if (this.getInput() == null) {
            Ui.taskNotGiven();
        } else {
            String[] temp = input.split(" /from ", 2);
            this.task = temp[0];
            String[] temp2 = temp.length > 1 ? temp[1].split(" /to ", 2) : null;
            this.startDate = temp2 == null ? null : temp2[0];
            this.endDate = temp2 != null && temp2.length > 1 ? temp[1] : null;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            ui.taskNotGiven();
        } else if (startDate == null) {
            System.out.println("Start date not given");
        } else if (endDate == null) {
            System.out.println("End date not given");
        } else {
            ui.taskAdded("Event");
            tasks.add(new Event(task, startDate, endDate, 0), storage);
        }
    }
}
