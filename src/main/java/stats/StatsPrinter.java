package stats;

import core.ProcessingResult;

public class StatsPrinter {

    public static void printShort(ProcessingResult r) {
        if (r.hasIntegers()) {
            System.out.println(r.getIntegersFileName() + ": итог = " + r.getIntegersStats().count);
        }
        if (r.hasFloats()) {
            System.out.println(r.getFloatsFileName() + ": итог = " + r.getFloatsStats().count);
        }
        if (r.hasStrings()) {
            System.out.println(r.getStringsFileName() + ": итог = " + r.getStringStats().count);
        }
    }

    public static void printFull(ProcessingResult r) {
        if (r.hasIntegers()) {
            System.out.println(r.getIntegersFileName() + ":");
            System.out.println("  итог = " + r.getIntegersStats().count);
            System.out.println("  минимальное значение = " + r.getIntegersStats().minString());
            System.out.println("  максимальное значение = " + r.getIntegersStats().maxString());
            System.out.println("  сумма = " + r.getIntegersStats().sumString());
            System.out.println("  среднее значение = " + r.getIntegersStats().avgString());
        }
        if (r.hasFloats()) {
            System.out.println(r.getFloatsFileName() + ":");
            System.out.println("  итог = " + r.getFloatsStats().count);
            System.out.println("  минимальное значение = " + r.getFloatsStats().minString());
            System.out.println("  максимальное значение = " + r.getFloatsStats().maxString());
            System.out.println("  сумма = " + r.getFloatsStats().sumString());
            System.out.println("  среднее значение = " + r.getFloatsStats().avgString());
        }
        if (r.hasStrings()) {
            System.out.println(r.getStringsFileName() + ":");
            System.out.println("  итог = " + r.getStringStats().count);
            System.out.println("  минимальная длина = " + r.getStringStats().minLenString());
            System.out.println("  максимальная длина = " + r.getStringStats().maxLenString());
        }
    }
}