package ekud.memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Task;
import ekud.tasks.Todo;

/**
 * Handles loading and saving task data to a file for persistent storage.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a {@code Storage} object and initializes the storage file.
     * <p>
     * If the file does not exist, it creates the necessary directories and the file itself.
     * </p>
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.createNewFile()) {
                System.out.println("File list created at: " + file.getAbsolutePath());
            } else {
                System.out.println("Saved list exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the storage file and returns them as a list.
     *
     * @return A list of tasks loaded from the file. Returns an empty list if the file is empty.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public ArrayList<Task> loadFileContent() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Initializing list!");
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            String[] line = s.nextLine().split("\\|");
            System.out.print(i + 1 + ". ");
            switch (line[0]) {
            case "T": {
                list.add(new Todo(line[1], Integer.parseInt(line[2])));
                break;
            }
            case "D": {
                list.add(new Deadline(line[1], line[2], Integer.parseInt(line[3])));
                break;
            }
            case "E": {
                list.add(new Event(line[1], line[2], line[3], Integer.parseInt(line[4])));
                break;
            }
            default:
                assert false : line[0];
                break;
            }
            i++;
        }
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return new ArrayList<>();
        } else {
            return list;
        }
    }

    /**
     * Saves the current task list to the storage file, overwriting existing content.
     *
     * @param list The task list to be saved.
     */
    public void saveToFile(TaskList list) {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : list.getList()) {
                if (task instanceof Todo) {
                    writer.write("T|" + task.getName() + "|" + task.getDone() + "\n");
                } else if (task instanceof Deadline d) {
                    writer.write("D|" + d.getName() + "|" + d.getDue_string() + "|" + d.getDone() + "\n");
                } else if (task instanceof Event e) {
                    writer.write("E|" + e.getName() + "|" + e.getStart_string() + "|"
                            + e.getEnd_string() + "|" + e.getDone() + "\n");
                } else {
                    assert false : "Invalid data should not be written into file";
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }
}
