package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        return ui.showUnknown();
    }

}
