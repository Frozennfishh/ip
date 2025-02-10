package ekud.memory;

import ekud.tasks.Task;

/**
 * Represents a pairing of an index and a task.
 * <p>
 * This class is used to associate a task with its position in a list,
 * maintaining a 1-based index for user-friendly display.
 * </p>
 */
public class IndexTaskPair {
    private final int index;
    private final Task task;

    /**
     * Constructs an {@code IndexTaskPair} with the specified index and task.
     * <p>
     * The index is incremented by 1 to follow a 1-based numbering convention.
     * </p>
     *
     * @param index The zero-based position of the task in a list (converted to 1-based).
     * @param task  The task associated with this index.
     */
    public IndexTaskPair(int index, Task task) {
        this.index = index + 1;
        this.task = task;
    }

    /**
     * Returns a formatted string representing the task and its assigned index.
     * <p>
     * The output format is: {@code "<index>. <task display string>"}.
     * </p>
     *
     * @return A string representation of the indexed task.
     */
    public String indexTaskPairDisplay() {
        return index + ". " + task.display();
    }
}
