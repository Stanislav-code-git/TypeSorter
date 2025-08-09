package stats;

public class StringStats {
    public long count = 0;
    public Integer minLen = null;
    public Integer maxLen = null;

    public void accept(String s) {
        count++;
        int len = s.length();
        if (minLen == null || len < minLen) minLen = len;
        if (maxLen == null || len > maxLen) maxLen = len;
    }

    public String minLenString() { return minLen == null ? "n/a" : String.valueOf(minLen); }
    public String maxLenString() { return maxLen == null ? "n/a" : String.valueOf(maxLen); }
}