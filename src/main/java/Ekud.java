import java.io.*;
import java.util.ArrayList;

public class Ekud {
    public static void main(String[] args) throws IOException {
        intro();
        goodbye();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));

        reader.close();
        writer.close();
    }

    public static void intro() {
        String logo = "\nEEEEE K  KK U   U DDD   !\n"
                + "E     KKK   U   U D  D  !\n"
                + "EEEEE K     U   U D   D !\n"
                + "E     KKK   UU UU D  D   \n"
                + "EEEEE K  KK  UUU  DDD   !\n";
        System.out.println("____________________________\n");
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________\n");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________\n");
        System.exit(0);
    }
}
