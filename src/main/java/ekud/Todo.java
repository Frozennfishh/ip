package ekud;

public class Todo extends Task {
    public Todo(String name, int done) {
        super(name, done);
        System.out.println(display());
    }

    public String display() {
        return "[T][" + (done == 1 ? "X" : " ") + "] " + name;
    }
}
