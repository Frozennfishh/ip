package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Todo;
import ekud.ui.Ui;

public class TodoCommand extends Command{
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            ui.taskNotGiven();
        } else {
            ui.taskAdded("Todo");
            tasks.add(new Todo(this.getInput(), 0), storage);
        }
    }
}
