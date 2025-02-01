package ekud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;
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
                case null, default: break;
            }
            i++;
        }
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return null;
        } else {
            return list;
        }
    }

    public void saveToFile(TaskList list) {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : list.getList()) {
                if (task instanceof Todo) {
                    writer.write("T|" + task.name + "|" + task.done + "\n");
                } else if (task instanceof Deadline d) {
                    writer.write("D|" + d.name + "|" + d.getDue_string() + "|" + d.done + "\n");
                } else if (task instanceof Event e) {
                    writer.write("E|" + e.name + "|" + e.getStart_string() + "|" + e.getEnd_string() + "|" + e.done + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }
}
