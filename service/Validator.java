package service;

public class Validator {
    public static boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }
    public static boolean positiveInt(int n) {
        return n > 0;
    }
    public static boolean matchDate(String s) {
        return s != null && s.matches("\\d{2}-\\d{2}-\\d{4}");
    }
    public static void require(boolean condition, String message) {
        if (!condition) throw new IllegalArgumentException(message);
    }
}
