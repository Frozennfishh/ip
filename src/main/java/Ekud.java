import javax.swing.*;
import java.io.*;
import java.util.*;

public class Ekud {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        intro();

        while (true) {
            String temp = scanner.nextLine();
            String[] input = temp.split(" ");
            if (Objects.equals(input[0], "bye")) {
                break;
            } else if (Objects.equals(input[0], "list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).display();
                    }
                }
                buffer();
            } else if (Objects.equals(input[0], "mark")) {
                if (input.length == 1 || !isInteger(input[1], 10)) {
                    System.out.println("Wrong input, try again!");
                } else if (Integer.parseInt(input[1]) > list.size()) {
                    System.out.println("This task does not exist :( Try again!");
                } else {
                    int index = Integer.parseInt(input[1]) - 1;
                    list.get(index).setDone();
                    System.out.println("Yippee marking this task as done!");
                    list.get(index).display();
                }
                buffer();
            } else if (Objects.equals(input[0], "unmark")) {
                if (input.length == 1 || !isInteger(input[1], 10)) {
                    System.out.println("Wrong input, try again!");
                } else if (Integer.parseInt(input[1]) > list.size()) {
                    System.out.println("This task does not exist :( Try again!");
                } else {
                    int index = Integer.parseInt(input[1]) - 1;
                    list.get(index).setUndone();
                    System.out.println("Awww marking this task undone :(");
                    list.get(index).display();
                }
                buffer();
            } else {
                System.out.println("added: " + temp);
                list.add(new Task(temp));
                buffer();
            }
        }
        goodbye();
    }

    public static void intro() {
        String logo = "\nEEEEE K  KK U   U DDD   !\n"
                + "E     KKK   U   U D  D  !\n"
                + "EEEEE K     U   U D   D !\n"
                + "E     KKK   UU UU D  D   \n"
                + "EEEEE K  KK  UUU  DDD   !";
        System.out.println("____________________________\n");
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("╱|、\n" +
                "(^ˎ ^7  \n" +
                "|、˜〵          \n" +
                "じしˍ,)ノ\n");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________\n");
    }

    public static void buffer() {
        System.out.println("ฅ^•ﻌ•^ฅ");
        System.out.println("____________________________\n");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________\n");
        System.exit(0);
    }

    static class Task {
        int done = 0;
        String name;

        public Task(String name) {
            this.name = name;
        }

        public void setDone() {
            this.done = 1;
        }

        public void setUndone() {
            this.done = 0;
        }

        public void display() {
            System.out.println("[" + (done == 1 ? "X" : " ") + "] " + name);
        }
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
