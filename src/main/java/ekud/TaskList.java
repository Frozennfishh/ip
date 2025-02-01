package ekud;

import java.io.FileNotFoundException;
import java.time.LocalDate;
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

    public void dueCheck(LocalDate dueDate) {
        ArrayList<IndexTaskPair> undone = new ArrayList<>();
        ArrayList<IndexTaskPair> done = new ArrayList<>();

        for (Task task : list) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getDue() == null) continue;
                if (((Deadline) task).getDue().toLocalDate().equals(dueDate)) {
                    if (task.done == 0) {
                        undone.add(new IndexTaskPair(list.indexOf(task), task));
                    } else {
                        done.add(new IndexTaskPair(list.indexOf(task), task));
                    }
                }
            } else if (task instanceof Event) {
                if (((Event) task).getEnd() == null) continue;
                if (((Event) task).getEnd().toLocalDate().equals(dueDate)) {
                    if (task.done == 0) {
                        undone.add(new IndexTaskPair(list.indexOf(task), task));
                    } else {
                        done.add(new IndexTaskPair(list.indexOf(task), task));
                    }
                }
            }
        }

        System.out.println("Here are the tasks that are due on " + dueDate + ":");
        System.out.println("Undone:");
        for (IndexTaskPair pair : undone) {
            pair.IndexTaskPairDisplay();
        }
        System.out.println("\n" + "Done:");
        for (IndexTaskPair pair : done) {
            pair.IndexTaskPairDisplay();
        }
    }
}
