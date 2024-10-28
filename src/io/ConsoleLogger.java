package io;

import static constants.ConfigConstants.PRINT_LOGS;

public class ConsoleLogger {

    @SuppressWarnings("unused")
    private class Colours {
        private static final String RESET  = "\u001B[0m";
        private static final String BLACK  = "\u001B[30m";
        private static final String RED    = "\u001B[31m";
        private static final String GREEN  = "\u001B[32m";
        private static final String YELLOW = "\u001B[33m";
        private static final String BLUE   = "\u001B[34m";
        private static final String PURPLE = "\u001B[35m";
        private static final String CYAN   = "\u001B[36m";
        private static final String WHITE  = "\u001B[37m";

        private Colours() {
            throw new IllegalStateException("Utility class");
        }
    }

    public static void logGreen(String message) {
        if (PRINT_LOGS) {
            System.out.print(Colours.GREEN);
            System.out.print(message);
            System.out.println(Colours.RESET);
        }
    }

    public static void logWhite(String message) {
        if (PRINT_LOGS) {
            System.out.print(message);
        }
    }
    
    public static void logError(String message, Exception e) {
        if (PRINT_LOGS) {
            System.out.println(Colours.RED);
            System.err.printf("ERROR: %s%n", message);

            System.out.print(Colours.YELLOW);
            e.printStackTrace();
            System.out.print(Colours.RESET);
        }
    }

    public static void logError(String message) {
        if (PRINT_LOGS) {
            System.out.print(Colours.RED);
            System.err.printf("ERROR: %s", message);
            System.out.println(Colours.RESET);
        }
    }

    private ConsoleLogger() {
        throw new IllegalStateException("Utility class");
    }
}
