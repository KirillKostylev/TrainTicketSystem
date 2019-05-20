package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.view;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Printer {

    public static void printToPrinter(String printData) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new OutputPrinter(printData));
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
}
