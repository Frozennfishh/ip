package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        }
        if (Parser.isValidIndex(this.getInput(), tasks)) {
            return ui.taskDoesNotExist();
        } else {
            String temp = ui.markDone(tasks, Integer.parseInt(this.getInput()) - 1);
            storage.saveToFile(tasks);
            return temp;
        }
    }
}
