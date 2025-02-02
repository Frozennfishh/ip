package ekud.ui;

import ekud.memory.Storage;
import ekud.memory.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String[] readLine() {
        return scanner.nextLine().split(" ", 2);
    }

    public void listEmpty() {
        System.out.println("List is empty! Yippee!");
    }

    public void intro() {
        String logo = """

                EEEEE K  KK U   U DDD   !
                E     KKK   U   U D  D  !
                EEEEE K     U   U D   D !
                E     KKK   UU UU D  D  \s
                EEEEE K  KK  UUU  DDD   !""";
        System.out.println("____________________________\n");
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("""
                ╱|
                (^ˎ ^7 \s
                |、 〵         \s
                じしˍ,)ノ
                """);
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________\n");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        buffer();
        System.exit(0);
    }

    public void buffer() {
        System.out.println("ฅ^•ﻌ•^ฅ");
        System.out.println("____________________________\n");
    }

    public void taskDoesNotExist() {
        System.out.println("This task does not exist :( Try again!");
    }

    public void taskNotGiven() {
        System.out.println("No task given, try again!");
    }

    public void markDone(TaskList tasks, int index) {
        tasks.get(index).setDone();
        System.out.println("Yippee marking this task as done!");
        System.out.println(tasks.get(index).display());
        tasks.leftCheck();
    }

    public void markUndone(TaskList tasks, int index) {
        tasks.get(index).setUndone();
        System.out.println("Awww marking this task undone :(");
        System.out.println(tasks.get(index).display());
        tasks.leftCheck();
    }

    public void taskAdded(String s) {
        System.out.println("Gotcha, " + s + " task added!");
    }

    public void delete(TaskList tasks, int index, Storage storage) {
        System.out.println("Omgie, removing this task from the list!");
        System.out.println(tasks.get(index).display());
        tasks.remove(index, storage);
    }

    public void unknown() {
        System.out.println("I don't understand ;-; Try again!");
    }
}
