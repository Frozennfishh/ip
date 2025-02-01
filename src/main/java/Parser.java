public class Parser {


    public Parser() {

    }
    public static boolean indexChecker(String s, TaskList t) {
        return isNotInteger(s, 10) || Integer.parseInt(s) > t.size();
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
