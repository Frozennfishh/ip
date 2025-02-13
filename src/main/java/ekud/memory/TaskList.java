package ekud.memory;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import ekud.command.EventCommand;
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
    private final ArrayList<Task> list;

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
                //task has no due date
                if (((Deadline) task).getDue() == null) {
                    continue;
                }
                //due date does not match input
                if (!((Deadline) task).getDue().toLocalDate().equals(dueDate)) {
                    continue;
                }
                //sort task to Done and Undone list
                doneChecker(undone, done, task);
            } else if (task instanceof Event) {
                //task has no due date
                if (((Event) task).getEnd() == null) {
                    continue;
                }
                //due date does not match input
                if (!((Event) task).getEnd().toLocalDate().equals(dueDate)) {
                    continue;
                }
                //sort task to Done and Undone list
                doneChecker(undone, done, task);
            } else {
                assert false : task.display();
            }
        }

        if (undone.isEmpty() && done.isEmpty()) {
            return "There is no task due on " + dueDate + "!";
        }
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

    /**
     * Categorizes a task as either undone or done and adds it to the corresponding list.
     * <p>
     * If the task is not completed, it is added to the {@code undone} list.
     * If the task is completed, it is added to the {@code done} list.
     * If the task has an invalid done value, an assertion error is thrown.
     * </p>
     *
     * @param undone The list of undone tasks.
     * @param done   The list of completed tasks.
     * @param task   The task to be categorized.
     */
    private void doneChecker(ArrayList<IndexTaskPair> undone, ArrayList<IndexTaskPair> done, Task task) {
        if (task.getDone() == 0) {
            undone.add(new IndexTaskPair(list.indexOf(task), task));
        } else if (task.getDone() == 1) {
            done.add(new IndexTaskPair(list.indexOf(task), task));
        } else {
            assert false : "Done value for " + task.display() + " is invalid";
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

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> res = new ArrayList<>();
        for (Task task : this.list) {
            if (task instanceof Event) {
                res.add((Event) task);
            }
        }
        return res;
    }
}
