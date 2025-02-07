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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDate dueDate = Parser.getDate(this.getInput());
        if (this.getInput() == null || dueDate == null) {
            ui.taskNotGiven();
        } else {
            tasks.dueCheck(dueDate);
        }
    }
}
