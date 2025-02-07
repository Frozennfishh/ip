package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

public class FindCommand extends Command{
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.findTaskPrint(tasks.findTask(this.getInput()));
    }
}
