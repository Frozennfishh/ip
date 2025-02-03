package ekud.ui;

import java.time.LocalDate;

import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Todo;

public class Command {
    private boolean isExit = false;
    private String command;
    private String input;
    public Command(String[] input) {
        this.command = input[0];
        this.input = input.length > 1 ? input[1] : null;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command) {
        case "bye": {
            isExit = true;
            break;
        }

        case "list": {
            if (tasks.isEmpty()) {
                ui.listEmpty();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).display());
                }
            }
            break;
        }

        case "clear": {
            System.out.println("Okies, clearing list!");
            tasks.clear();
            storage.saveToFile(tasks);
            break;
        }

        case "mark": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            if (Parser.indexChecker(input, tasks)) {
                ui.taskDoesNotExist();
            } else {
                ui.markDone(tasks, Integer.parseInt(input) - 1);
                storage.saveToFile(tasks);
            }
            break;
        }

        case "unmark": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            if (Parser.indexChecker(input, tasks)) {
                ui.taskDoesNotExist();
            } else {
                ui.markUndone(tasks, Integer.parseInt(input) - 1);
                storage.saveToFile(tasks);
            }
            break;
        }

        case "todo": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            ui.taskAdded("Todo");
            tasks.add(new Todo(input, 0), storage);
            break;
        }

        case "deadline": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            String[] temp = input.split(" /by ", 2);
            if (temp.length == 1) {
                System.out.println("Deadline not given! Try again!");
            } else {
                ui.taskAdded("Deadline");
                tasks.add(new Deadline(temp[0], temp[1], 0), storage);
            }
            break;
        }

        case "event": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            String[] temp = input.split(" /from ", 2);
            if (temp.length == 1) {
                System.out.println("Start time not given! Try again!");
            } else {
                String[] temp2 = temp[1].split(" /to ", 2);
                if (temp2.length == 1) {
                    System.out.println("End time not given! Try again!");
                } else {
                    ui.taskAdded("Event");
                    tasks.add(new Event(temp[0], temp2[0], temp2[1], 0), storage);
                }
            }
            break;
        }

        case "delete": {
            if (input == null) {
                ui.taskNotGiven();
                break;
            }
            if (Parser.indexChecker(input, tasks)) {
                ui.taskDoesNotExist();
            } else {
                ui.delete(tasks, Integer.parseInt(input) - 1, storage);
            }
            break;
        }

        case "due": {
            LocalDate dueDate = Parser.getDate(input);
            if (input == null || dueDate == null) {
                ui.taskNotGiven();
                break;
            }
            tasks.dueCheck(dueDate);
            break;
        }

        default: ui.unknown();

        }
        ui.buffer();
    }

    public boolean isExit() {
        return this.isExit;
    }
}
