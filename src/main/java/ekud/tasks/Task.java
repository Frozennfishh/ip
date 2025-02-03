package ekud.tasks;

/**
 * Represents a generic task.
 * <p>
 * The {@code Task} class provides the basic functionality for tracking a task's name and completion status.
 * It serves as the base class for specific task types such as {@code Todo}, {@code Deadline}, and {@code Event}.
 * </p>
 */
public class Task {
    private int done;
    private String name;

    /**
     * Constructs a {@code Task} with the specified name and completion status.
     *
     * @param name The name or description of the task.
     * @param done The completion status (1 for done, 0 for not done).
     */
    public Task(String name, int done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks the task as completed.
     */
    public void setDone() {
        this.done = 1;
    }

    /**
     * Marks the task as not completed.
     */
    public void setUndone() {
        this.done = 0;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return {@code 1} if the task is completed, {@code 0} if it is not.
     */
    public int getDone() {
        return this.done;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name or description of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a formatted string representation of the task.
     * <p>
     * The output format is: {@code "[X] Task Name"} for completed tasks
     * and {@code "[ ] Task Name"} for incomplete tasks.
     * </p>
     *
     * @return A formatted string representing the task.
     */
    public String display() {
        return "[" + (done == 1 ? "X" : " ") + "] " + name;
    }
}
