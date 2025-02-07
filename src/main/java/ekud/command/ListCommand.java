package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return ui.listEmpty();
        } else {
            StringBuilder sb = new StringBuilder();

            System.out.println("Here are the tasks in your list:");
            sb.append("Here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).display());
                sb.append(i + 1).append(". ").append(tasks.get(i).display()).append("\n");
            }

            return sb.toString();
        }
    }
}
