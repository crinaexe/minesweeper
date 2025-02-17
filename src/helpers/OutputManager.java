package helpers;

public class OutputManager {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    public static void greeting(int nrOfBombs){
        System.out.println("The current game has " + ANSI_RED + nrOfBombs  + ANSI_RESET + " bombs. Flag them all to win!");
        System.out.println(ANSI_GREEN + "Let's start the game! Enter your first coordinates (row, column). Enter after each coordinate" + ANSI_RESET );
    }

    public static void printError(String text){
        System.out.println(ANSI_RED + text + ANSI_RESET );
    }

    public static String colorCodeNumbers(String square){
        return switch (square) {
            case "0" -> " ";
            case "1" -> ANSI_GREEN + square + ANSI_RESET;
            case "2" -> ANSI_YELLOW + square + ANSI_RESET;
            case "3" -> ANSI_PURPLE + square + ANSI_RESET;
            case "4" -> ANSI_BLUE + square + ANSI_RESET;
            case "x" -> square;
            default -> ANSI_RED + square + ANSI_RESET;
        };
    }
}
