package core;

import util.NumberNormalizer;

public class LineClassifier {

    public DataType classify(String raw) {
        if (raw == null) return DataType.STRING;
        String t = raw.trim();
        if (t.isEmpty()) return DataType.STRING;

        String n = NumberNormalizer.normalize(t);

        if (n.matches("[+-]?\\d+")) return DataType.INTEGER;

        if (n.matches("[+-]?(?:\\d+\\.\\d*|\\d*\\.\\d+)(?:[eE][+-]?\\d+)?")
                || n.matches("[+-]?\\d+(?:[eE][+-]?\\d+)")) {
            return DataType.FLOAT;
        }

        return DataType.STRING;
    }
}