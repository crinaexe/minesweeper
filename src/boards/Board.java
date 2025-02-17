package boards;


public abstract class Board {
    protected final int NR_OF_BOMBS = 5;
    public final int BOARD_SIZE = 5;
    protected final int BOMB = 9;

    public Board() throws Exception {
        if(NR_OF_BOMBS > BOARD_SIZE * BOARD_SIZE){
            throw new Exception("There are too many bombs for the size of the board");
        }
    }
}
