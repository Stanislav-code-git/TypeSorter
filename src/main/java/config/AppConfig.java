package config;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class AppConfig {
    private final Path outputDir;
    private final String prefix;
    private final boolean append;
    private final StatsMode statsMode;
    private final List<java.nio.file.Path> inputFiles;

    public AppConfig(Path outputDir, String prefix, boolean append, StatsMode statsMode, List<Path> inputFiles) {
        this.outputDir = outputDir;
        this.prefix = prefix == null ? "" : prefix;
        this.append = append;
        this.statsMode = statsMode == null ? StatsMode.NONE : statsMode;
        this.inputFiles = List.copyOf(inputFiles);
    }

    public Path getOutputDir() { return outputDir; }
    public String getPrefix() { return prefix; }
    public boolean isAppend() { return append; }
    public StatsMode getStatsMode() { return statsMode; }
    public List<Path> getInputFiles() { return Collections.unmodifiableList(inputFiles); }
}