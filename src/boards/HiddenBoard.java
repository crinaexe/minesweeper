package boards;

import helpers.Coordinates;
import java.util.Random;

public class HiddenBoard extends Board{
    private final int[][] board;

    public HiddenBoard(Coordinates coordinates) throws Exception{
        board = new int[BOARD_SIZE][BOARD_SIZE];
        createRandomBoard(coordinates);
    }



    private void createRandomBoard(Coordinates coordinates) {
        placeBombs(coordinates);
        fillBoardWithNumbers();
    }
    private void placeBombs(Coordinates coordinates){
        Random rand = new Random();
        for (int bomb = 0; bomb < NR_OF_BOMBS; bomb++) {
            int i = rand.nextInt(BOARD_SIZE);
            int j = rand.nextInt(BOARD_SIZE);
            if(!canPlaceBomb(coordinates, i, j)) {
                bomb--;
            } else {
                board[i][j] = BOMB;
            }

        }
    }

    private boolean canPlaceBomb(Coordinates coordinates, int i, int j){
        if(board[i][j] == BOMB)return false;
        if((coordinates.getX() == i) && (coordinates.getY() == j))return false;
        for (int rowMod = -1; rowMod <= 1; rowMod++) {
            for (int colMod = -1; colMod <= 1; colMod++) {
                    if ((i + rowMod == coordinates.getX() )&& (j + colMod == coordinates.getY())) {
                        return false;
                    }
            }
        }
        return true;
    }

    private void fillBoardWithNumbers(){
        for (int k = 0; k < BOARD_SIZE; k++) {
            for (int l = 0; l < BOARD_SIZE; l++) {
                if (board[k][l] != BOMB) {
                    for (int rowMod = -1; rowMod <= 1; rowMod++) {
                        for (int colMod = -1; colMod <= 1; colMod++) {
                            try {
                                if (board[k + rowMod][l + colMod] == BOMB) {
                                    board[k][l]++;
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                }
            }
        }

    }



    public boolean isBombAt(Coordinates coordinates){
        try {
            return board[coordinates.getX()][coordinates.getY()] == BOMB;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return false;
    }

    public int getValueAtPosition(Coordinates coordinates){
        try {
            return board[coordinates.getX()][coordinates.getY()];
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return -1;

    }

}
