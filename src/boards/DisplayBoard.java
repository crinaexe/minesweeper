package boards;

import helpers.Coordinates;
import helpers.OutputManager;

public class DisplayBoard extends Board {
    private final String[][] board;

    public DisplayBoard() throws Exception {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        generateDisplayBoard();
    }


    private void generateDisplayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "x";
            }
        }
        OutputManager.greeting(NR_OF_BOMBS);
    }

    public void setElement(Coordinates coordinates, String value) {
        try {
            board[coordinates.getX()][coordinates.getY()] = value;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    public boolean isSquareUncovered(Coordinates coordinates, String value) {
        try {
            return board[coordinates.getX()][coordinates.getY()].equals(value);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return false;
    }

    public boolean uncoverElement(Coordinates coordinates, HiddenBoard hiddenBoard) {
        if(hiddenBoard.isBombAt(coordinates)){
            setElement(coordinates,"✷");
            return true;
        } else if(isSquareUncovered(coordinates,String.valueOf(hiddenBoard.getValueAtPosition(coordinates)))){
            OutputManager.printError("This square is already visible, try again");
        } else {
            setElement(coordinates, String.valueOf(hiddenBoard.getValueAtPosition(coordinates)));
            clearBoardOnSelection(coordinates, hiddenBoard);
        }
        return false;
    }

    private void clearBoardOnSelection(Coordinates coordinates, HiddenBoard hiddenBoard) {

        if (0 == hiddenBoard.getValueAtPosition(coordinates)) {
            for (int rowMod = -1; rowMod <= 1; rowMod++) {
                for (int colMod = -1; colMod <= 1; colMod++) {
                    Coordinates neighbour = new Coordinates(coordinates.getX() + rowMod, coordinates.getY() + colMod);

                    try {
                        if(board[neighbour.getX()][neighbour.getY()].equals("x")){
                            if (9 != hiddenBoard.getValueAtPosition(neighbour)) {
                                setElement(neighbour, String.valueOf(hiddenBoard.getValueAtPosition(neighbour)));

                                if (0 == hiddenBoard.getValueAtPosition(neighbour)) {
                                    clearBoardOnSelection(neighbour, hiddenBoard);
                                }
                            }
                        }
                    }  catch (ArrayIndexOutOfBoundsException ignored) {

                    }

                }
            }
        } else {
            setElement(coordinates, String.valueOf(hiddenBoard.getValueAtPosition(coordinates)));
        }
    }

    public String getValueAtPosition(Coordinates coordinates) {
        try {
            return board[coordinates.getX()][coordinates.getY()];
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return "";

    }


    public void markFlag(Coordinates coordinates) {
        setElement(coordinates, "⚑");
    }

    public void unMarkFlag(Coordinates coordinates) {
        setElement(coordinates, "x");
    }

    @Override
    public String toString() {
        String toString = "      ";
        for (int i = 0; i < BOARD_SIZE; i++) {
            toString += i + "  ";
        }
        toString += "\n     ";
        for (int i = 0; i < BOARD_SIZE * 3; i++) {
            toString += "-";
        }
        toString += "\n";
        for (int i = 0; i < BOARD_SIZE; i++) {
            toString += i + "  |  ";
            for (int j = 0; j < BOARD_SIZE; j++) {
                    toString += OutputManager.colorCodeNumbers(board[i][j]) + "  ";

            }
            toString += "\n";
        }

        return toString;
    }

}
