package ekud.tasks;

/**
 * Represents a basic to-do task.
 * <p>
 * A {@code Todo} task has a name and a completion status, but no time constraints.
 * It extends the {@code Task} class and overrides the display format.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with a specified name and completion status.
     *
     * @param name The name of the to-do task.
     * @param done The completion status of the task (1 for done, 0 for not done).
     */
    public Todo(String name, int done) {
        super(name, done);
        System.out.println(display());
    }

    @Override
    /**
     * Returns a formatted string representation of the to-do task.
     * <p>
     * The output format is {@code "[T][X] Task Name"} for completed tasks
     * and {@code "[T][ ] Task Name"} for incomplete tasks.
     * </p>
     *
     * @return A formatted string representing the to-do task.
     */
    public String display() {
        return "[T][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName();
    }
}
