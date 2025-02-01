public class Task {
    int done;
    String name;

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

    public String display() {
        return "[" + (done == 1 ? "X" : " ") + "] " + name;
    }
}
