package ekud.tasks;

public class Todo extends Task {
    public Todo(String name, int done) {
        super(name, done);
        System.out.println(display());
    }

    public String display() {
        return "[T][" + (this.getDone() == 1 ? "X" : " ") + "] " + this.getName();
    }
}
