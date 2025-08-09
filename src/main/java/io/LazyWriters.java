package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LazyWriters {

    private final Path integersPath;
    private final Path floatsPath;
    private final Path stringsPath;
    private final boolean append;

    private BufferedWriter integers;
    private BufferedWriter floats;
    private BufferedWriter strings;

    public LazyWriters(Path outputDir, String prefix, boolean append) {
        this.integersPath = outputDir.resolve((prefix == null ? "" : prefix) + "integers.txt");
        this.floatsPath   = outputDir.resolve((prefix == null ? "" : prefix) + "floats.txt");
        this.stringsPath  = outputDir.resolve((prefix == null ? "" : prefix) + "strings.txt");
        this.append = append;
    }

    public void writeInteger(String s) throws IOException {
        if (integers == null) integers = open(integersPath, append);
        integers.write(s);
        integers.newLine();
    }

    public void writeFloat(String s) throws IOException {
        if (floats == null) floats = open(floatsPath, append);
        floats.write(s);
        floats.newLine();
    }

    public void writeString(String s) throws IOException {
        if (strings == null) strings = open(stringsPath, append);
        strings.write(s);
        strings.newLine();
    }

    public boolean hasIntegers() { return integers != null; }
    public boolean hasFloats() { return floats != null; }
    public boolean hasStrings() { return strings != null; }

    public String integersFileName() { return integersPath.getFileName().toString(); }
    public String floatsFileName() { return floatsPath.getFileName().toString(); }
    public String stringsFileName() { return stringsPath.getFileName().toString(); }

    public void closeQuietly() {
        close(integers);
        close(floats);
        close(strings);
    }

    private static BufferedWriter open(Path path, boolean append) throws IOException {
        if (append) {
            return Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } else {
            return Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    private static void close(BufferedWriter w) {
        if (w != null) { try { w.close(); } catch (IOException ignored) {} }
    }
}