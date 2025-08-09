package util;

import java.math.BigDecimal;

public class Numbers {
    public static BigDecimal parseOrNull(String s) {
        try {
            return new BigDecimal(NumberNormalizer.normalize(s));
        } catch (Exception e) {
            return null;
        }
    }
}