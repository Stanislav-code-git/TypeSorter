package cli;

import config.AppConfig;
import config.StatsMode;
import errors.AppException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsParser {

    public AppConfig parse(String[] args) {
        if (args == null || args.length == 0) {
            throw new AppException("Не указаны аргументы запуска");
        }

        Path outputDir = null;
        String prefix = "";
        boolean append = false;
        boolean seenShort = false;
        boolean seenFull = false;
        List<Path> inputs = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            switch (a) {
                case "-o":
                    ensureHasNext(args, i, "-o");
                    outputDir = Paths.get(args[++i]);
                    break;
                case "-p":
                    ensureHasNext(args, i, "-p");
                    prefix = args[++i];
                    if (containsInvalidPathChars(prefix)) {
                        throw new AppException("Префикс содержит недопустимые символы для имени файла");
                    }
                    break;
                case "-a":
                    append = true;
                    break;
                case "-s":
                    seenShort = true;
                    break;
                case "-f":
                    seenFull = true;
                    break;
                default:
                    if (a.startsWith("-")) {
                        throw new AppException("Неизвестная опция: " + a);
                    }
                    inputs.add(Paths.get(a));
            }
        }

        if (seenShort && seenFull) {
            throw new AppException("Опции -s и -f взаимоисключающие. Укажите только одну.");
        }
        if (inputs.isEmpty()) {
            throw new AppException("Не указаны входные файлы");
        }

        StatsMode statsMode = seenFull ? StatsMode.FULL : (seenShort ? StatsMode.SHORT : StatsMode.NONE);
        if (outputDir == null) outputDir = Paths.get(".");

        return new AppConfig(outputDir, prefix, append, statsMode, inputs);
    }

    private static void ensureHasNext(String[] args, int i, String opt) {
        if (i + 1 >= args.length) {
            throw new AppException("После " + opt + " требуется значение");
        }
    }

    private static boolean containsInvalidPathChars(String s) {
        return s != null && s.matches(".*[<>:\"/\\\\|?*].*");
    }
}