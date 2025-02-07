package ekud.command;

import java.time.LocalDate;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

public class DueCommand extends Command {
    private LocalDate dueDate;
    public DueCommand(String input) {
        super(input);
        this.dueDate = input == null ? null : Parser.getDate(this.getInput());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        if (dueDate == null) {
            return ui.invalidDateGiven();
        } else {
            return tasks.dueCheck(dueDate);
        }
    }
}
