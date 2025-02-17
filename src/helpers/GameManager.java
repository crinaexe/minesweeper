package helpers;

import boards.HiddenBoard;
import boards.DisplayBoard;

public class GameManager {
    private boolean gameOver = false;
    private InputManager inputManager;
    private DisplayBoard displayBoard;
    private HiddenBoard hiddenBoard;


    public GameManager() {
    }

    public void start() throws Exception {
        initiateGame();

        while (!gameOver){
            printBoard();
            String command = inputManager.checkCommand();
            Coordinates coordinates = inputManager.getNumberSelection();
            performMenuAction(command,coordinates);
        }
    }

    private void initiateGame() throws Exception {
        displayBoard =  new DisplayBoard();
        printBoard();
        inputManager = new InputManager(displayBoard.BOARD_SIZE);
        Coordinates coordinates = inputManager.getNumberSelection();

        //Bomb is generated after player's first move, to prevent an instant lose
        hiddenBoard = new HiddenBoard(coordinates);
        displayBoard.uncoverElement(coordinates,hiddenBoard);
    }

    private void performMenuAction(String option, Coordinates coordinates){
        switch (option) {
            case "bo" -> markFlagAndCheckForWin(coordinates);
            case "un" -> displayBoard.unMarkFlag(coordinates);
            case "re" -> uncover(coordinates);
            default -> OutputManager.printError("Command unknown");
        }
    }

    private void markFlagAndCheckForWin(Coordinates coordinates){
        displayBoard.markFlag(coordinates);
        if(playerWon()){
            System.out.println("You won!");
            gameOver = true;
        }
    }

    private void uncover(Coordinates coordinates){
        gameOver = displayBoard.uncoverElement(coordinates,hiddenBoard);
        if(gameOver){
            printBoard();
            OutputManager.printError("Sorry! You lost!");
        }

    }



    private boolean playerWon(){
        for (int i = 0; i < displayBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < displayBoard.BOARD_SIZE; j++) {
                if(!elementsMatch( new Coordinates(i,j))){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean elementsMatch(Coordinates coordinates){
        if(displayBoard.getValueAtPosition(coordinates).equals(String.valueOf(hiddenBoard.getValueAtPosition(coordinates))) || (displayBoard.getValueAtPosition(coordinates).equals("x") && !hiddenBoard.isBombAt(coordinates))){
            return true;
        }
        return displayBoard.getValueAtPosition(coordinates).equals("⚑") && hiddenBoard.isBombAt(coordinates);
    }

    private void printBoard(){
        System.out.println("Current board:");
        System.out.println(displayBoard.toString());
    }


}
