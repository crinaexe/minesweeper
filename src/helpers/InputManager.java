package helpers;

import java.util.Scanner;

public class InputManager {
    private Scanner scanner = new Scanner(System.in);

    private int x = 999;
    private int y = 999;
    private int boardSize;

    public InputManager(int boardSize) {
        this.boardSize = boardSize;
    }

    public Coordinates getNumberSelection(){
        scanForNumbers();
        while (x >= boardSize || y >= boardSize){
            System.out.println("Please provide two coordinates of the square you want to uncover. Values must be 0-" + (boardSize-1) +"\nEnter after each coordinate");
            scanForNumbers();
        }
        Coordinates coordinates = new Coordinates(x,y);
        return coordinates;
    }


    private void scanForNumbers(){
        x = Integer.parseInt(scanner.nextLine());
        y = Integer.parseInt(scanner.nextLine());
    }


    public String checkCommand(){
        System.out.println("To reveal a square, input re. To flag a bomb, input bo. To unflag a square, input un. \nPlease provide two coordinates of the square you want to uncover. Values must be 0-" + (boardSize-1) +"\nFirst enter the row and then the column\nEnter after each command/ coordinate");
        String option = scanner.nextLine();
        while (option == "re" || option == "bo" ||option == "un"){
            System.out.println("Please choose one of the correct commands (re, bo OR un)");
            option = scanner.nextLine();
        }
        return option;
    }
}
