package stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumericStats {
    public long count = 0;
    public BigDecimal min = null;
    public BigDecimal max = null;
    public BigDecimal sum = BigDecimal.ZERO;

    public void accept(BigDecimal v) {
        count++;
        if (min == null || v.compareTo(min) < 0) min = v;
        if (max == null || v.compareTo(max) > 0) max = v;
        sum = sum.add(v);
    }

    public String minString() { return min == null ? "n/a" : min.stripTrailingZeros().toPlainString(); }
    public String maxString() { return max == null ? "n/a" : max.stripTrailingZeros().toPlainString(); }
    public String sumString() { return count == 0 ? "n/a" : sum.stripTrailingZeros().toPlainString(); }

    public String avgString() {
        if (count == 0) return "n/a";
        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), new MathContext(20, RoundingMode.HALF_UP));
        return avg.stripTrailingZeros().toPlainString();
    }
}