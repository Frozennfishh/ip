package ekud.command;

import java.time.LocalDate;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

public class DueCommand extends Command{
    public DueCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDate dueDate = Parser.getDate(this.getInput());
        if (this.getInput() == null || dueDate == null) {
            return ui.taskNotGiven();
        } else {
            return tasks.dueCheck(dueDate);
        }
    }
}
