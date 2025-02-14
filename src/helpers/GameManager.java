package helpers;

import boards.HiddenBoard;
import boards.PlayerBoard;

public class GameManager {
    private boolean gameOver = false;
    private InputManager inputManager;
    private PlayerBoard playerBoard;
    private HiddenBoard hiddenBoard;
    private Coordinates coordinates;

    public GameManager() {
    }

    public void start() throws Exception {
        initiateGame();

        while (!gameOver){
            printBoard();
            String command = inputManager.checkCommand();
            coordinates = inputManager.getNumberSelection();
            menu(command,coordinates);
            if(playerWon()){
                System.out.println("You won!");
                gameOver = true;
            }
        }
    }

    private void initiateGame() throws Exception {
        playerBoard =  new PlayerBoard();
        printBoard();
        inputManager = new InputManager(playerBoard.BOARD_SIZE);
        System.out.println("Let's start the game! Enter your first coordinates (row, column). Enter after each coordinate");
        coordinates = inputManager.getNumberSelection();

        //Bomb is generated after player's first move, to prevent an instant lose
        hiddenBoard = new HiddenBoard(coordinates);
        uncover(coordinates);
    }

    private void menu(String option, Coordinates coordinates){
        switch (option) {
            case "bo" -> playerBoard.markFlag(coordinates);
            case "un" -> playerBoard.unMarkFlag(coordinates);
            case "re" -> uncover(coordinates);
            default -> System.out.println("command unknown");
        }
    }

    private void uncover(Coordinates coordinates){
        if(hiddenBoard.isBombAt(coordinates)){
            playerBoard.setElement(coordinates,"✷");
            printBoard();
            System.out.println("Sorry! You lost!");
            gameOver = true;
        } else if(playerBoard.squareUncovered(coordinates,String.valueOf(hiddenBoard.getValueAtPosition(coordinates)))){
            System.out.println("This square is already visible, try again");
        } else {
            playerBoard.uncoverElement(coordinates,hiddenBoard);
        }

    }



    private boolean playerWon(){
        for (int i = 0; i < playerBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < playerBoard.BOARD_SIZE; j++) {
                if(!elementsMatch( new Coordinates(i,j))){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean elementsMatch(Coordinates coordinates){
        if(playerBoard.getValueAtPosition(coordinates).equals(String.valueOf(hiddenBoard.getValueAtPosition(coordinates))) || (playerBoard.getValueAtPosition(coordinates).equals("x") && !hiddenBoard.isBombAt(coordinates))){
            return true;
        }
        return playerBoard.getValueAtPosition(coordinates).equals("⚑") && hiddenBoard.isBombAt(coordinates);
    }

    private void printBoard(){
        System.out.println("Current board:");
        System.out.println(playerBoard.toString());
    }


}
