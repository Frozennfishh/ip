import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Ekud {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        intro();

        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                break;
            } else if (Objects.equals(input, "list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty!");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                }
                buffer();
            } else {
                System.out.println("added: " + input);
                list.add(input);
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
}
