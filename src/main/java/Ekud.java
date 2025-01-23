import java.util.*;

public class Ekud {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        intro();

        while (true) {
            String[] temp = scanner.nextLine().split(" ", 2);
            String command = temp[0];
            String input = temp.length > 1 ? temp[1] : null;
            if (Objects.equals(command, "bye")) {
                break;
            }  else if (Objects.equals(command, "list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i).display());
                    }
                }
                buffer();
            } else if (input == null) {
                System.out.println("No task given, try again!");
                buffer();
            } else if (Objects.equals(command, "mark")) {
                if (isNotInteger(input, 10) || Integer.parseInt(input) > list.size()) {
                    System.out.println("This task does not exist :( Try again!");
                } else {
                    int index = Integer.parseInt(input) - 1;
                    list.get(index).setDone();
                    System.out.println("Yippee marking this task as done!");
                    System.out.println(list.get(index).display());
                }
                buffer();
            } else if (Objects.equals(command, "unmark")) {
                if (isNotInteger(input, 10) || Integer.parseInt(input) > list.size()) {
                    System.out.println("This task does not exist :( Try again!");
                } else {
                    int index = Integer.parseInt(input) - 1;
                    list.get(index).setUndone();
                    System.out.println("Awww marking this task undone :(");
                    System.out.println(list.get(index).display());
                }
                buffer();
            } else if (Objects.equals(command, "todo")) {
                System.out.println("Gotcha, Todo task added!");
                list.add(new Todo(input));
                leftCheck(list);
                buffer();
            } else if (Objects.equals(command, "deadline")) {
                String[] temp2 = input.split(" /by ", 2);
                if (temp2.length == 1) {
                    System.out.println("Deadline not given! Try again!");
                } else {
                    String task = temp2[0];
                    String dueDate = temp2[1];
                    System.out.println("Gotcha, Deadline task added!");
                    list.add(new Deadline(task, dueDate));
                    leftCheck(list);
                }
                buffer();
            } else if (Objects.equals(command, "event")) {
                String[] temp2 = input.split(" /from ", 2);
                if (temp2.length == 1) {
                    System.out.println("Start time not given! Try again!");
                } else {
                    String[] temp3 = temp2[1].split(" /to ",2);
                    if (temp3.length == 1) {
                        System.out.println("End time not given! Try again!");
                    } else {
                        String task = temp2[0];
                        String startTime = temp3[0];
                        String endTime = temp3[1];
                        System.out.println("Gotcha, Event added!");
                        list.add(new Event(task, startTime, endTime));
                        leftCheck(list);
                    }
                }
                buffer();
            } else {
                System.out.println("I don't understand ;-; Try again!");
            }
        }
        goodbye();
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

        public String display() {
            return "[" + (done == 1 ? "X" : " ") + "] " + name;
        }
    }

    static class Todo extends Task {
        public Todo(String name) {
            super(name);
            System.out.println(display());
        }

        public String display() {
            return "[T][" + (done == 1 ? "X" : " ") + "] " + name;
        }
    }

    static class Deadline extends Task {
        String due;

        public Deadline(String task, String dueDate) {
            super(task);
            this.due = dueDate;
            System.out.println(display());
        }

        public String display() {
            return "[D][" + (done == 1 ? "X" : " ") + "] " + name +
                    " (by: " + due + ")";
        }
    }

    static class Event extends Task {
        String start;
        String end;

        public Event(String name, String start, String end) {
            super(name);
            this.start = start;
            this.end = end;
            System.out.println(display());
        }

        public String display() {
            return "[E][" + (done == 1 ? "X" : " ") + "] " + name +
                    " (from: " + start + " to: " + end + ")";
        }
    }

    private static void leftCheck(ArrayList<Task> list) {
        int left = 0;
        for (Task task : list) {
            if (task.done == 0) {
                left += 1;
            }
        }
        System.out.println("You're left with " + left + " tasks left to do!");
    }

    public static void intro() {
        String logo = """

                EEEEE K  KK U   U DDD   !
                E     KKK   U   U D  D  !
                EEEEE K     U   U D   D !
                E     KKK   UU UU D  D  \s
                EEEEE K  KK  UUU  DDD   !""";
        System.out.println("____________________________\n");
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("""
                ╱|、
                (^ˎ ^7 \s
                |、˜〵         \s
                じしˍ,)ノ
                """);
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


    public static boolean isNotInteger(String s, int radix) {
        if(s.isEmpty()) return true;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return true;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return true;
        }
        return false;
    }
}
