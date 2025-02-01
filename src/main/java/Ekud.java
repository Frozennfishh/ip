import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Ekud {
    private static void loadFileContent(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File file = new File(filePath);

        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.createNewFile()) {
                System.out.println("File list created at: " + file.getAbsolutePath());
            } else {
                System.out.println("Saved list exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        System.out.println("Initializing list!");

        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            String[] line = s.nextLine().split("\\|");
            System.out.print(i + 1 + ". ");
            switch (line[0]) {
                case "T": {
                    list.add(new Todo(line[1], Integer.parseInt(line[2])));
                    break;
                }
                case "D": {
                    list.add(new Deadline(line[1], line[2], Integer.parseInt(line[3])));
                    break;
                }
                case "E": {
                    list.add(new Event(line[1], line[2], line[3], Integer.parseInt(line[4])));
                    break;
                }
                case null, default: break;
            }
            i++;
        }
        if (list.isEmpty()) {
            System.out.println("List is empty!");
        }
    }

    private static void saveToFile(String filePath, ArrayList<Task> list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : list) {
                if (task instanceof Todo) {
                    writer.write("T|" + task.name + "|" + task.done + "\n");
                } else if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    writer.write("D|" + d.name + "|" + d.due_string + "|" + d.done + "\n");
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    writer.write("E|" + e.name + "|" + e.start + "|" + e.end + "|" + e.done + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "data/list.txt";

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        loadFileContent(filePath, list);
        intro();

        while (true) {
            String[] temp = scanner.nextLine().split(" ", 2);
            String command = temp[0];
            String input = temp.length > 1 ? temp[1] : null;
            if (Objects.equals(command, "bye")) {
                break;
            }  else if (Objects.equals(command, "list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty! Yippee!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i).display());
                    }
                }
                buffer();
            } else if (Objects.equals(command, "clear")) {
                System.out.println("Okies, clearing list!");
                list.clear();
                saveToFile(filePath,list);
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
                    saveToFile(filePath, list);
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
                    saveToFile(filePath, list);
                }
                buffer();
            } else if (Objects.equals(command, "todo")) {
                System.out.println("Gotcha, Todo task added!");
                list.add(new Todo(input, 0));
                leftCheck(list);
                saveToFile(filePath, list);
                buffer();
            } else if (Objects.equals(command, "deadline")) {
                String[] temp2 = input.split(" /by ", 2);
                if (temp2.length == 1) {
                    System.out.println("Deadline not given! Try again!");
                } else {
                    String task = temp2[0];
                    String dueDate = temp2[1];
                    System.out.println("Gotcha, Deadline task added!");
                    list.add(new Deadline(task, dueDate, 0));
                    saveToFile("data/list.txt", list); // Save updated list
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
                        list.add(new Event(task, startTime, endTime, 0));
                        leftCheck(list);
                        saveToFile(filePath, list);
                    }
                }
                buffer();
            } else if (Objects.equals(command, "delete")) {
                if (isNotInteger(input, 10) || Integer.parseInt(input) > list.size()) {
                    System.out.println("This task does not exist :( Try again!");
                } else {
                    int task = Integer.parseInt(input) - 1;
                    System.out.println("Omgie, removing this task from the list!");
                    System.out.println(list.get(task).display());
                    list.remove(task);
                    leftCheck(list);
                    saveToFile(filePath, list);
                }
                buffer();
            } else {
                System.out.println("I don't understand ;-; Try again!");
            }
        }
        goodbye();
    }


    static class Task {
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

    static class Todo extends Task {
        public Todo(String name, int done) {
            super(name, done);
            System.out.println(display());
        }

        public String display() {
            return "[T][" + (done == 1 ? "X" : " ") + "] " + name;
        }
    }

    static class Deadline extends Task {
        private final LocalDateTime due;
        private final String due_string;

        public Deadline(String task, String dueDate, int done) {
            super(task, done);
            if (DateTimeParser.parseDateTime(dueDate) != null) {
                this.due = DateTimeParser.parseDateTime(dueDate);
            } else if (DateTimeParser.parseDate(dueDate) != null) {
                this.due = DateTimeParser.parseDate(dueDate);
            } else {
                this.due = null;
            }
            this.due_string = dueDate;
            System.out.println(display());
        }



        public String display() {
            if (due == null) {
                return "[D][" + (done == 1 ? "X" : " ") + "] " + name + " (by: " + due_string + ")";
            } else {
                DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
                return "[D][" + (done == 1 ? "X" : " ") + "] " + name + " (by: " + due.format(outputFormat) + ")";
            }
        }
    }

    static class Event extends Task {
        private final String start_string;
        private final String end_string;

        private final LocalDateTime start;
        private final LocalDateTime end;

        public Event(String name, String start, String end, int done) {
            super(name, done);
            this.start_string = start;
            this.end_string = end;
            if (DateTimeParser.parseDateTime(start) != null) {
                this.start = DateTimeParser.parseDateTime(start);
            } else if (DateTimeParser.parseDate(start) != null) {
                this.start = DateTimeParser.parseDate(start);
            } else {
                this.start = null;
            }
            if (DateTimeParser.parseDateTime(end) != null) {
                this.end = DateTimeParser.parseDateTime(end);
            } else if (DateTimeParser.parseDate(end) != null) {
                this.end = DateTimeParser.parseDate(end);
            } else {
                this.end = null;
            }
            System.out.println(display());
        }

        public String display() {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            String s = start != null ? start.format(outputFormat) : this.start_string;
            String e = end != null ? end.format(outputFormat) : this.end_string;
            return "[E][" + (done == 1 ? "X" : " ") + "] " + name +
                    " (from: " + s + " to: " + e + ")";
        }
    }

    static class DateTimeParser {
        private static final String[] DATE_TIME_PATTERNS = {
                "d/M/yyyy HHmm",
                "dd/MM/yyyy HH:mm",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm",
                "dd MMM yyyy HH:mm",
                "dd MMMM yyyy HH:mm",
                "EEE, dd MMM yyyy HH:mm",
                "EEEE, dd MMMM yyyy HH:mm"
        };

        private static final String[] DATE_PATTERNS = {
                "d/M/yyyy",
                "dd/MM/yyyy",
                "yyyy-MM-dd",
                "yyyy/MM/dd",
                "dd MMM yyyy",
                "dd MMMM yyyy",
                "EEE, dd MMM yyyy",
                "EEEE, dd MMMM yyyy"
        };

        public static LocalDateTime parseDateTime(String input) {
            for (String pattern : DATE_TIME_PATTERNS) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    return LocalDateTime.parse(input, formatter);
                } catch (DateTimeParseException ignored) {
                }
            }
            return null;
        }

        public static LocalDateTime parseDate(String input) {
            for (String pattern : DATE_PATTERNS) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    return LocalDate.parse(input, formatter).atTime(LocalTime.MIDNIGHT);
                } catch (DateTimeParseException ignored) {
                }
            }
            return null;
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
