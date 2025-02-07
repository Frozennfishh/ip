package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.ui.Ui;

public class DeleteCommand extends Command{
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        } else {
            if (Parser.isValidIndex(this.getInput(), tasks)) {
                return ui.taskDoesNotExist();
            } else {
                return ui.delete(tasks, Integer.parseInt(this.getInput()) - 1, storage);
            }
        }
    }
}
