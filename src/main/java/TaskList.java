import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList(Storage storage) throws FileNotFoundException {
        this.list = storage.loadFileContent();
    }

    public void leftCheck() {
        int left = 0;
        for (Task task : list) {
            if (task.done == 0) {
                left += 1;
            }
        }
        System.out.println("You're left with " + left + " tasks left to do!");
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void clear() {
        list.clear();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task task, Storage storage) {
        list.add(task);
        this.leftCheck();
        storage.saveToFile(this);
    }

    public void remove(int index, Storage storage) {
        list.remove(index);
        this.leftCheck();
        storage.saveToFile(this);
    }
}
