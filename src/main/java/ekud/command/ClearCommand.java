package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

public class ClearCommand extends Command{
    public ClearCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Okies, clearing list!");
        tasks.clear();
        storage.saveToFile(tasks);
        return "Okies, clearing list!";
    }
}
