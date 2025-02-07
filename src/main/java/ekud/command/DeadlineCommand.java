package ekud.command;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.tasks.Deadline;
import ekud.ui.Ui;

public class DeadlineCommand extends Command{
    String task;
    String dueDate;

    public DeadlineCommand(String input) {
        super(input);
        if (this.getInput() == null) {
            Ui.taskNotGiven();
        } else {
            String[] temp = input.split(" /by ", 2);
            this.task = temp[0];
            this.dueDate = temp.length > 1 ? temp[1] : null;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.getInput() == null) {
            //Ui.taskNotGiven();
        } else {
            if (dueDate == null) {
                System.out.println("Deadline not given! Try again!");
            } else {
                ui.taskAdded("Deadline");
                tasks.add(new Deadline(task, dueDate, 0), storage);
            }
        }

    }
}
