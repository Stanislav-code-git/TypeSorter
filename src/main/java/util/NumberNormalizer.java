package util;

public class NumberNormalizer {
    public static String normalize(String s) {
        if (s == null) return "";
        return s.trim().replace(',', '.');
    }
}