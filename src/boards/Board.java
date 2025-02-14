package boards;


public abstract class Board {
    public final int NR_OF_BOMBS = 3;
    public  final int BOARD_SIZE = 5;
    public final int BOMB = 9;

    public Board() throws Exception {
        if(NR_OF_BOMBS > BOARD_SIZE * BOARD_SIZE){
            throw new Exception("There are too many bombs for the size od the board");
        }
    }




    public String toString(Object[][] board) {
        String toString = "";
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                toString = toString + String.valueOf(board[i][j]) + "  ";
            }
            toString = toString +"\n";
        }
        return toString;
    }
}
