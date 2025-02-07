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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        } else {
            return ui.taskAdded("Todo") + "\n" +
                    tasks.add(new Todo(this.getInput(), 0), storage);
        }
    }
}
