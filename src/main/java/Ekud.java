import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Ekud {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Ekud (String filePath) throws FileNotFoundException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
        ui = new Ui();
    }

    public void run() {
        ui.intro();
        boolean isExit = false;
        while (!isExit) {
            Command c = new Command(ui.readLine());
            c.execute(taskList, ui, storage);
            isExit = c.isExit();
        }
        ui.goodbye();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Ekud("data/list.txt").run();
    }

/*
        while (true) {
           else if (input == null) {
                System.out.println("No task given, try again!");
                buffer();
            }
            } else if (Objects.equals(command, "due")) {
                LocalDate dueDate;
                ArrayList<IndexTaskPair> undone = new ArrayList<>();
                ArrayList<IndexTaskPair> done = new ArrayList<>();
                if (DateTimeParser.parseDateTime(input) != null) {
                    dueDate = DateTimeParser.parseDateTime(input).toLocalDate();
                } else if (DateTimeParser.parseDate(input) != null) {
                    dueDate = DateTimeParser.parseDate(input).toLocalDate();
                } else {
                    System.out.println("Due date not valid, try again!");
                    buffer();
                    continue;
                }

                for (Task task : list) {
                    if (task instanceof Deadline) {
                        if (((Deadline) task).getDue().toLocalDate().equals(dueDate)) {
                            if (task.done == 0) {
                                undone.add(new IndexTaskPair(list.indexOf(task), task));
                            } else {
                                done.add(new IndexTaskPair(list.indexOf(task), task));
                            }
                        }
                    } else if (task instanceof Event) {
                        if (((Event) task).getEnd().toLocalDate().equals(dueDate)) {
                            if (task.done == 0) {
                                undone.add(new IndexTaskPair(list.indexOf(task), task));
                            } else {
                                done.add(new IndexTaskPair(list.indexOf(task), task));
                            }
                        }
                    }
                }

                System.out.println("Here are the tasks that are due on " + dueDate + ":");
                System.out.println("Undone:");
                for (IndexTaskPair pair : undone) {
                    pair.IndexTaskPairDisplay();
                }
                System.out.println("\n" + "Done:");
                for (IndexTaskPair pair : done) {
                    pair.IndexTaskPairDisplay();
                }
                buffer();
            } else {
                System.out.println("I don't understand ;-; Try again!");
            }
        }
        goodbye();
    }
       */
}
