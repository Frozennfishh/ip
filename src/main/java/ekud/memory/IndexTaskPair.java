package ekud.memory;

import ekud.tasks.Task;

public class IndexTaskPair {
    private int index;
    private final Task task;

    public IndexTaskPair (int index, Task task) {
        this.index = index + 1;
        this.task = task;
    }

    public void IndexTaskPairDisplay() {
        System.out.println(index + ". " + task.display());
    }
}
