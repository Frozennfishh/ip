package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

public class FindCommand extends Command{
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTaskPrint(tasks.findTask(this.getInput()));
    }
}
