package core;

import stats.NumericStats;
import stats.StringStats;

public class ProcessingResult {
    private final boolean hasIntegers;
    private final boolean hasFloats;
    private final boolean hasStrings;

    private final String integersFileName;
    private final String floatsFileName;
    private final String stringsFileName;

    private final NumericStats integersStats;
    private final NumericStats floatsStats;
    private final StringStats stringStats;

    public ProcessingResult(boolean hasIntegers, boolean hasFloats, boolean hasStrings,
                            String integersFileName, String floatsFileName, String stringsFileName,
                            NumericStats integersStats, NumericStats floatsStats, StringStats stringStats) {
        this.hasIntegers = hasIntegers;
        this.hasFloats = hasFloats;
        this.hasStrings = hasStrings;
        this.integersFileName = integersFileName;
        this.floatsFileName = floatsFileName;
        this.stringsFileName = stringsFileName;
        this.integersStats = integersStats;
        this.floatsStats = floatsStats;
        this.stringStats = stringStats;
    }

    public boolean hasIntegers() { return hasIntegers; }
    public boolean hasFloats() { return hasFloats; }
    public boolean hasStrings() { return hasStrings; }

    public String getIntegersFileName() { return integersFileName; }
    public String getFloatsFileName() { return floatsFileName; }
    public String getStringsFileName() { return stringsFileName; }

    public NumericStats getIntegersStats() { return integersStats; }
    public NumericStats getFloatsStats() { return floatsStats; }
    public StringStats getStringStats() { return stringStats; }
}