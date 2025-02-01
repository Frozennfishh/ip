package ekud.tasks;

public class Task {
    private int done;
    private String name;

    public Task(String name, int done) {
        this.name = name;
        this.done = done;
    }

    public void setDone() {
        this.done = 1;
    }

    public void setUndone() {
        this.done = 0;
    }
    public int getDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

    public String display() {
        return "[" + (done == 1 ? "X" : " ") + "] " + name;
    }
}
