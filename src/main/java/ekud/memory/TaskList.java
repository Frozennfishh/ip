package ekud.memory;

import ekud.memory.IndexTaskPair;
import ekud.memory.Storage;
import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Task;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList(Storage storage) throws FileNotFoundException {
        this.list = storage.loadFileContent();
    }

    public void leftCheck() {
        int left = 0;
        for (Task task : list) {
            if (task.getDone() == 0) {
                left += 1;
            }
        }
        System.out.println("You're left with " + left + " tasks left to do!");
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void clear() {
        list.clear();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task task, Storage storage) {
        list.add(task);
        this.leftCheck();
        storage.saveToFile(this);
    }

    public void remove(int index, Storage storage) {
        list.remove(index);
        this.leftCheck();
        storage.saveToFile(this);
    }

    public void dueCheck(LocalDate dueDate) {
        ArrayList<IndexTaskPair> undone = new ArrayList<>();
        ArrayList<IndexTaskPair> done = new ArrayList<>();

        for (Task task : list) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getDue() == null) continue;
                if (((Deadline) task).getDue().toLocalDate().equals(dueDate)) {
                    if (task.getDone() == 0) {
                        undone.add(new IndexTaskPair(list.indexOf(task), task));
                    } else {
                        done.add(new IndexTaskPair(list.indexOf(task), task));
                    }
                }
            } else if (task instanceof Event) {
                if (((Event) task).getEnd() == null) continue;
                if (((Event) task).getEnd().toLocalDate().equals(dueDate)) {
                    if (task.getDone() == 0) {
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
    }

    /**
     * Searches for tasks that contain a specific keyword in their name.
     * <p>
     * This method iterates through the task list and returns a list of tasks
     * whose names contain the given input string.
     * </p>
     *
     * @param input The keyword to search for within task names.
     * @return An {@code ArrayList<Task>} containing tasks that match the search criteria.
     *         Returns an empty list if no matching tasks are found.
     */
    public ArrayList<Task> findTask(String input) {
        ArrayList<Task> output = new ArrayList<>();

        for (Task task : this.getList()) {
            if (task.getName().contains(input)) {
                output.add(task);
            }
        }

        return output;
    }
}
