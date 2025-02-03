package ekud.memory;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Task;

/**
 * Represents a list of tasks.
 * The {@code TaskList} class manages adding, removing, and checking tasks,
 * as well as interacting with the storage system.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a {@code TaskList} object by loading tasks from storage.
     *
     * @param storage The {@code Storage} instance to load tasks from.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public TaskList(Storage storage) throws FileNotFoundException {
        this.list = storage.loadFileContent();
    }

    /**
     * Checks how many tasks are left to complete and prints the count.
     */
    public void leftCheck() {
        int left = 0;
        for (Task task : list) {
            if (task.getDone() == 0) {
                left += 1;
            }
        }
        System.out.println("You're left with " + left + " tasks left to do!");
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves a task at a specified index.
     *
     * @param i The index of the task.
     * @return The {@code Task} at the given index.
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Clears all tasks from the list.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An {@code ArrayList<Task>} containing all tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a task to the list, updates storage, and checks remaining tasks.
     *
     * @param task The task to add.
     * @param storage The {@code Storage} instance used to save the task list.
     */
    public void add(Task task, Storage storage) {
        list.add(task);
        this.leftCheck();
        storage.saveToFile(this);
    }

    /**
     * Removes a task from the list, updates storage, and checks remaining tasks.
     *
     * @param index The index of the task to remove.
     * @param storage The {@code Storage} instance used to save the task list.
     */
    public void remove(int index, Storage storage) {
        list.remove(index);
        this.leftCheck();
        storage.saveToFile(this);
    }

    /**
     * Checks for tasks that are due on a specific date and prints them.
     * Prints list of tasks that are due on specified date.
     * Split by done and undone tasks.
     *
     * @param dueDate The {@code LocalDate} to check for due tasks.
     */
    public void dueCheck(LocalDate dueDate) {
        ArrayList<IndexTaskPair> undone = new ArrayList<>();
        ArrayList<IndexTaskPair> done = new ArrayList<>();

        for (Task task : list) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getDue() == null) {
                    continue;
                }
                if (((Deadline) task).getDue().toLocalDate().equals(dueDate)) {
                    if (task.getDone() == 0) {
                        undone.add(new IndexTaskPair(list.indexOf(task), task));
                    } else {
                        done.add(new IndexTaskPair(list.indexOf(task), task));
                    }
                }
            } else if (task instanceof Event) {
                if (((Event) task).getEnd() == null) {
                    continue;
                }
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
            pair.indexTaskPairDisplay();
        }
        System.out.println("\n" + "Done:");
        for (IndexTaskPair pair : done) {
            pair.indexTaskPairDisplay();
        }
    }
}
