package boards;

import helpers.Coordinates;

public class PlayerBoard extends Board {
    private final String[][] board;

    public PlayerBoard() throws Exception {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        generatePlayerBoard();
    }

    private void generatePlayerBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "x";
            }
        }
    }

    public void setElement(Coordinates coordinates, String value) {
        try {
            board[coordinates.getX()][coordinates.getY()] = value;
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public boolean squareUncovered(Coordinates coordinates, String value) {
        try {
            return board[coordinates.getX()][coordinates.getY()].equals(value);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }

    public void uncoverElement(Coordinates coordinates, HiddenBoard hiddenBoard) {
        setElement(coordinates, String.valueOf(hiddenBoard.getValueAtPosition(coordinates)));
        clearBoardOnSelection(coordinates, hiddenBoard);
    }

    public void clearBoardOnSelection(Coordinates coordinates, HiddenBoard hiddenBoard) {

        if (0 == hiddenBoard.getValueAtPosition(coordinates)) {
            for (int rowMod = -1; rowMod <= 1; rowMod++) {
                for (int colMod = -1; colMod <= 1; colMod++) {//iterate over the neighbours
                    Coordinates neighbour = new Coordinates(coordinates.getX() + rowMod, coordinates.getY() + colMod);

                    try {
                        if(board[neighbour.getX()][neighbour.getY()] == "x"){
                            if (0 == hiddenBoard.getValueAtPosition(neighbour)
                                    || 1 == hiddenBoard.getValueAtPosition(neighbour)) {
                                setElement(neighbour, String.valueOf(hiddenBoard.getValueAtPosition(neighbour)));

                                if (0 == hiddenBoard.getValueAtPosition(neighbour)) {
                                    clearBoardOnSelection(neighbour, hiddenBoard);
                                }
                            }
                        }
                    }  catch (ArrayIndexOutOfBoundsException e) {

                    }

                }
            }
        } else {
            setElement(coordinates, String.valueOf(hiddenBoard.getValueAtPosition(coordinates)));
        }
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
                    toString += board[i][j] + "  ";

            }
            toString += "\n";
        }

        return toString;
    }

    public String getValueAtPosition(Coordinates coordinates) {
        try {
            return board[coordinates.getX()][coordinates.getY()];
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return "";

    }
}
