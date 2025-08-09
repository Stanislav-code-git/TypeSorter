package io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputScanner {

    public List<Path> scanReadableFiles(List<Path> candidates) {
        List<Path> ok = new ArrayList<>();
        for (Path p : candidates) {
            if (!Files.exists(p)) {
                System.err.println("Предупреждение: файл не найден (пропущен): " + p);
                continue;
            }
            if (!Files.isRegularFile(p)) {
                System.err.println("Предупреждение: не обычный файл (пропущен): " + p);
                continue;
            }
            if (!Files.isReadable(p)) {
                System.err.println("Предупреждение: нет доступа на чтение (пропущен): " + p);
                continue;
            }
            ok.add(p);
        }
        return ok;
    }
}