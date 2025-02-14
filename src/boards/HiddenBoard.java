package boards;

import helpers.Coordinates;

import java.util.Random;

public class HiddenBoard extends Board{
    private int[][] board;

    public HiddenBoard(Coordinates coordinates) throws Exception{
        board = new int[BOARD_SIZE][BOARD_SIZE];
        createRandomBoard(coordinates);
    }



    private void createRandomBoard(Coordinates coordinates) {
        Random rand = new Random();
        for (int bomb = 0; bomb < NR_OF_BOMBS; bomb++) {
            int i = rand.nextInt(BOARD_SIZE);
            int j = rand.nextInt(BOARD_SIZE);
            if((board[i][j] == BOMB) || ((coordinates.getX() == i) && (coordinates.getY() == j))) {
                bomb--;
            } else {
                board[i][j] = BOMB;
            }


        }

        for (int k = 0; k < BOARD_SIZE; k++) {
            for (int l = 0; l < BOARD_SIZE; l++) {
                if (board[k][l] != BOMB) { //if I'm not a bomb
                    for (int rowMod = -1; rowMod <= 1; rowMod++) { //iterate over the neighbours
                        for (int colMod = -1; colMod <= 1; colMod++) {
                            try {
                                if (board[k + rowMod][l + colMod] == BOMB) { //if my neighbour is a bomb
                                    board[k][l]++;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
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
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }

    public int getValueAtPosition(Coordinates coordinates){
        try {
            return board[coordinates.getX()][coordinates.getY()];
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return -1;

    }

}
