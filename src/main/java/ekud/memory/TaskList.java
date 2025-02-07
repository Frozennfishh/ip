package ekud.memory;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.Task;

/**
 * Manages a list of tasks, providing methods for adding, removing, and searching tasks.
 * <p>
 * This class interacts with storage to persist task data.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a {@code TaskList} by loading tasks from storage.
     *
     * @param storage The storage instance used to load tasks.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public TaskList(Storage storage) throws FileNotFoundException {
        this.list = storage.loadFileContent();
    }

    /**
     * Counts and displays the number of incomplete tasks.
     *
     * @return A message indicating the number of tasks left to complete.
     */
    public String leftCheck() {
        int left = 0;
        for (Task task : list) {
            if (task.getDone() == 0) {
                left += 1;
            }
        }
        System.out.println("You're left with " + left + " tasks left to do!");
        return "You're left with " + left + " tasks left to do!";
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
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param i The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Removes all tasks from the list.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return A list containing all tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a task to the list, updates storage, and checks remaining tasks.
     *
     * @param task    The task to add.
     * @param storage The storage instance used to save the task list.
     * @return A message confirming the task addition along with the number of tasks left.
     */
    public String add(Task task, Storage storage) {
        list.add(task);
        storage.saveToFile(this);
        return task.display() + "\n" + this.leftCheck();
    }

    /**
     * Removes a task from the list, updates storage, and checks remaining tasks.
     *
     * @param index   The index of the task to remove.
     * @param storage The storage instance used to save the task list.
     */
    public void remove(int index, Storage storage) {
        list.remove(index);
        this.leftCheck();
        storage.saveToFile(this);
    }

    /**
     * Finds tasks that are due on a specific date.
     * <p>
     * Tasks are categorized into "Undone" and "Done" based on completion status.
     * </p>
     *
     * @param dueDate The date to check for due tasks.
     * @return A formatted string listing tasks due on the specified date.
     */
    public String dueCheck(LocalDate dueDate) {
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

        if (undone.isEmpty() && done.isEmpty()) {
            return "There is no task due on " + dueDate + "!";
        } else {
            StringBuilder sb = new StringBuilder();

            System.out.println("Here are the tasks that are due on " + dueDate + ":");
            System.out.println("Undone:");
            sb.append("Here are the tasks that are due on ").append(dueDate).append(":\n");
            sb.append("Undone:\n");

            for (IndexTaskPair pair : undone) {
                System.out.println(pair.indexTaskPairDisplay());
                sb.append(pair.indexTaskPairDisplay()).append("\n");
            }

            System.out.println("\n" + "Done:");
            sb.append("\nDone:\n");

            for (IndexTaskPair pair : done) {
                sb.append(pair.indexTaskPairDisplay()).append("\n");
            }

            return sb.toString();
        }
    }

    /**
     * Searches for tasks that contain a specific keyword in their name.
     *
     * @param input The keyword to search for.
     * @return A list of tasks that match the search criteria.
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
