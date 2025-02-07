package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Deadline;
import ekud.ui.Ui;

public class DeadlineCommand extends Command {
    private String task;
    private String dueDate;

    public DeadlineCommand(String input) {
        super(input);
        if (this.getInput() == null) {
            //Ui.taskNotGiven();
        } else {
            String[] temp = input.split(" /by ", 2);
            this.task = temp[0];
            this.dueDate = temp.length > 1 ? temp[1] : null;
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        if (this.getInput() == null) {
            return ui.taskNotGiven();
        } else {
            if (dueDate == null) {
                System.out.println("Deadline not given! Try again!");
                return "Deadline not given! Try again!";
            } else {
                return ui.taskAdded("Deadline") + "\n"
                        + this.getTasks().add(new Deadline(task, dueDate, 0), this.getStorage());

            }
        }
    }
}
