package core;

import config.AppConfig;
import errors.AppException;
import errors.FatalIoException;
import io.InputScanner;
import io.LazyWriters;
import stats.NumericStats;
import stats.StringStats;
import util.Numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProcessingService {

    public ProcessingResult run(AppConfig cfg) {
        ensureOutputDir(cfg.getOutputDir());

        List<Path> inputs = new InputScanner().scanReadableFiles(cfg.getInputFiles());
        if (inputs.isEmpty()) {
            throw new AppException("Ни один входной файл не доступен для чтения");
        }

        LazyWriters writers = new LazyWriters(cfg.getOutputDir(), cfg.getPrefix(), cfg.isAppend());

        NumericStats intStats = new NumericStats();
        NumericStats floatStats = new NumericStats();
        StringStats strStats = new StringStats();

        LineClassifier classifier = new LineClassifier();

        try {
            for (Path in : inputs) {
                try (BufferedReader reader = Files.newBufferedReader(in, StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        DataType t = classifier.classify(line);
                        switch (t) {
                            case INTEGER:
                                try {
                                    writers.writeInteger(line);
                                } catch (IOException e) {
                                    throw new FatalIoException("Не удалось открыть/записать " + writers.integersFileName(), e);
                                }
                                var vInt = Numbers.parseOrNull(line);
                                if (vInt != null) intStats.accept(vInt);
                                break;
                            case FLOAT:
                                try {
                                    writers.writeFloat(line);
                                } catch (IOException e) {
                                    throw new FatalIoException("Не удалось открыть/записать " + writers.floatsFileName(), e);
                                }
                                var vDec = Numbers.parseOrNull(line);
                                if (vDec != null) floatStats.accept(vDec);
                                break;
                            default:
                                try {
                                    writers.writeString(line);
                                } catch (IOException e) {
                                    throw new FatalIoException("Не удалось открыть/записать " + writers.stringsFileName(), e);
                                }
                                strStats.accept(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка чтения файла " + in + ": " + e.getMessage());
                }
            }
        } finally {
            writers.closeQuietly();
        }

        return new ProcessingResult(
                writers.hasIntegers(), writers.hasFloats(), writers.hasStrings(),
                writers.integersFileName(), writers.floatsFileName(), writers.stringsFileName(),
                intStats, floatStats, strStats
        );
    }

    private void ensureOutputDir(Path dir) {
        try {
            if (Files.exists(dir) && !Files.isDirectory(dir)) {
                throw new AppException("Указанный путь для вывода не является директорией: " + dir.toAbsolutePath());
            }
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new AppException("Ошибка создания директории вывода: " + e.getMessage(), e);
        }
    }
}