package cli;

public class UsagePrinter {
    public static void print() {
        System.err.println("Использование:");
        System.err.println("  TypeSorter [-o <outputDir>] [-p <prefix>] [-a] [-s | -f] <file1> [file2 ...]");
        System.err.println("Опции:");
        System.err.println("  -o <dir>   Папка вывода (по умолчанию текущая)");
        System.err.println("  -p <pref>  Префикс имён выходных файлов (например, result_)");
        System.err.println("  -a         Режим добавления (append) вместо перезаписи");
        System.err.println("  -s         Краткая статистика (только количество)");
        System.err.println("  -f         Полная статистика (числа: min/max/sum/avg; строки: count/minLen/maxLen)");
    }
}