# **Minesweeper Game**

_This game was made after the concept of minesweeper_

**Minesweeper**  is a classic single-player puzzle game that challenges players to clear a hidden grid without detonating any mines. The grid consists of hidden squares, some of which contain mines, and the player's goal is to reveal all the squares that do not contain mines while avoiding those that do.

---
## **Gameplay Instructions**
To start the game, simply run the main method.
1. **Objective**:
    - Flag all the bombs in the grid.

2. **Controls**:
    - In the console, enter the coordinates of a square you want to reveal to start the game.
    - After the game starts, you will be prompted to choose one of the 3 commands: reveal a square, flag a bomb or unflag a bomb.
3. **Hints**:
    - Numbers on revealed squares indicate how many mines are adjacent to that square (horizontally, vertically, and diagonally).
    - Use the numbers and logic to deduce the locations of the mines.

4. **Winning**:
    - The game is won when all mine squares are flagged properly. 

5. **Losing**:
    - The game is lost if you reveal a bomb.
   
6. **Changing the board size and bomb number:**
    - In the Board class, change the BOARD_SIZE and NR_OF_BOMBS values to change the size of the grid and the number of bombs.
    - We recommend the following difficulties: 
    -Easy: Board size is set to 5, number of bombs is set to 2
    - Hard: Board size is set to 10, number of bomb is set to 10

---
## **License**

This project is not licensed. All rights are reserved by the author. Please contact the author for permission to use, modify, or distribute this project.
