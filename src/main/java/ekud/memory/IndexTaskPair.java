package ekud.memory;

import ekud.tasks.Task;

/**
 * Represents a pair of an index and a task.
 * This class is used to associate a task with its position in a list.
 */
public class IndexTaskPair {
    private int index;
    private final Task task;

    /**
     * Constructs an {@code IndexTaskPair} with a given index and task.
     * The index is incremented by 1 to ensure it is **1-based**.
     *
     * @param index The position of the task in a list (0-based index, incremented by 1).
     * @param task  The task associated with this index.
     */
    public IndexTaskPair (int index, Task task) {
        this.index = index + 1;
        this.task = task;
    }

    /**
     * Displays the task with its assigned index.
     * Prints the task in the format: {@code "<index>. <task display string>"}.
     */
    public void IndexTaskPairDisplay() {
        System.out.println(index + ". " + task.display());
    }
}
