import cli.ArgumentsParser;
import cli.UsagePrinter;
import config.AppConfig;
import config.StatsMode;
import core.ProcessingResult;
import core.ProcessingService;
import errors.AppException;
import stats.StatsPrinter;

public class TypeSorter {
    public static void main(String[] args) {
        try {
            AppConfig config = new ArgumentsParser().parse(args);
            ProcessingResult result = new ProcessingService().run(config);

            if (config.getStatsMode() == StatsMode.SHORT) {
                StatsPrinter.printShort(result);
            } else if (config.getStatsMode() == StatsMode.FULL) {
                StatsPrinter.printFull(result);
            }
        } catch (AppException e) {
            System.err.println(e.getMessage());
            UsagePrinter.print();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Критическая ошибка: " + e.getMessage());
            System.exit(2);
        }
    }
}