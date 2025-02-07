package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

public class UnknownCommand extends Command{
    public UnknownCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
    }

    @Override
    public String getString() {
        return Ui.showUnknown();
    }
}
