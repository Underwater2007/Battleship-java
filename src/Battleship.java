
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 309954
 */
public class Battleship {

    Board computerBoard;
    Board playerBoard;
    Board playerHitMissBoard;
    Board computerHitMissBoard;
    int[] shipLength = {2, 3, 3, 4, 5};
    //int[] shipLength = {2};
    int[] playerShipHits;
    int[] computerShipHits;

    //different colors
    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String blue = "\u001B[34m";
    String yellow = "\u001B[33m";
    String reset = "\u001B[0m";

    public Battleship() {
        //creates new boards
        computerBoard = new Board();
        playerBoard = new Board();
        playerHitMissBoard = new Board();
        computerHitMissBoard = new Board();
        //tracks hits for player and computer for ship sank
        playerShipHits = new int[shipLength.length];
        computerShipHits = new int[shipLength.length];

        displayInstruction();
        placeComputerShips();
        displayComputerBoard();
        System.out.println("");
        placePlayerShips();
        displayPlayerBoard();
        hitMiss();
    }
    private void displayInstruction(){
        //instructions
        System.out.println("Welcome To Battleship");
        System.out.println("Both the player and computer have 5 ships of different length");
        System.out.println("ShipLengths {2,3,3,4,5}");
        System.out.println("The player will have to place ships manually entering rotation and location");
        System.out.println("If player or comptuer hit the opponents ship they go again");
        System.out.println("If player hits the computer there will be a 7 displayed on the hit miss board and it miss there will be a 6");
        System.out.println("same goes for the comptuer but on the computer hit miss board");
        System.out.println("If player or computer distroys one of the opponents ships they will get a message of Ship Snak");
        System.out.println("If either the player or the comptuer sink all of the opponets ships they win");
        System.out.println("");
    }

    private void displayComputerBoard() {
        //displays the computer board where computers ship will spawn 
        char letter = 65;
        System.out.println(red + "Computer Board" + reset);
        for (int i = 0; i < computerBoard.getGrid().length; i++) {
            System.out.print(" ");
            System.out.print(letter);
            letter++;
        }

        System.out.println("");
        for (int i = 0; i < computerBoard.getGrid()[0].length; i++) {
            System.out.print(i);
            System.out.print(" ");
            computerBoard.display(i);
            System.out.println("");

        }
    }

    private void displayPlayerBoard() {
        //displays the player board where Player ship will spawn 
        char letter = 65;
        System.out.println(blue + "Player Board" + reset);
        for (int i = 0; i < playerBoard.getGrid().length; i++) {
            System.out.print(" ");
            System.out.print(letter);
            letter++;
        }
        System.out.println("");
        for (int i = 0; i < playerBoard.getGrid()[0].length; i++) {
            System.out.print(i);
            System.out.print(" ");
            playerBoard.display(i);
            System.out.println("");
        }
    }

    private void displayPlayerHitMissBoard() {
        //displays the hitmiss board for player  
        char letter = 65;
        System.out.println(green + "Player Hit Miss Board" + reset);
        for (int i = 0; i < playerHitMissBoard.getGrid().length; i++) {
            System.out.print(" ");
            System.out.print(letter);
            letter++;
        }
        System.out.println("");
        for (int i = 0; i < playerHitMissBoard.getGrid()[0].length; i++) {
            System.out.print(i);
            System.out.print(" ");
            playerHitMissBoard.display(i);
            System.out.println("");
        }
    }

    private void displayComputerHitMissBoard() {
        //displays the hitmiss board for Computer
        char letter = 65;
        System.out.println(green + "Computer Hit Miss Board" + reset);
        for (int i = 0; i < computerHitMissBoard.getGrid().length; i++) {
            System.out.print(" ");
            System.out.print(letter);
            letter++;
        }
        System.out.println("");
        for (int i = 0; i < computerHitMissBoard.getGrid()[0].length; i++) {
            System.out.print(i);
            System.out.print(" ");
            computerHitMissBoard.display(i);
            System.out.println("");
        }
    }

    private boolean isCorrect(int r, int c, Board board) {
        //this method checks if either the player or computer hit eachothers boat
        for (int i = 0; i < shipLength.length; i++) {
            if (board.getGrid()[r][c] >= 1 && board.getGrid()[r][c] <= shipLength.length) {
                //hit
                return true;
            } else if (board.getGrid()[r][c] == 0) {
                //miss
                return false;
            }
        }
        return false;
    }

    private void checkHitMiss(String currentPlayer, String Opp, int r, int c, Board board1, Board board2, int[] shipHit) {
        //this method executes what changes happen after the player/computer hits 
        if (isCorrect(r, c, board1)) {
            System.out.println(blue + currentPlayer + " Hit The " + Opp + "'s Boat" + reset);
            int shipNumber = board1.getGrid()[r][c] - 1;
            shipHit[shipNumber]++;

            // Check if this hit sank the ship
            if (shipHit[shipNumber] == shipLength[shipNumber]) {
                System.out.println(red + currentPlayer + " sank " + Opp + "'s ship: " + (shipNumber + 1) + reset);
            }
            //if it is a hit then the hitmiss board for the player/computer will change the number of the (r,c) to 7
            board2.getGrid()[r][c] = 7;

        } else {
            //if it is a hit then the hitmiss board for the player/computer will change the number of the (r,c) to 6
            board2.getGrid()[r][c] = 6;
            System.out.println(red + currentPlayer + " Missed" + reset);
        }
    }

    private boolean isTargetAvailable(int r, int c, Board board) {
        //checks if the player/computer can shot at the spot 
        if (board.getGrid()[r][c] == 7 || board.getGrid()[r][c] == 6) {
            return false;
        }
        return true;
    }

    private void hitMiss() {
        Scanner in = new Scanner(System.in);
        int r;
        int c;
        //player
        int totalPlayerShipParts = 0;
        for (int i = 0; i < shipLength.length; i++) {
            //calculates how many individual ship parts there are in the game
            totalPlayerShipParts += shipLength[i];
        }
        int count = 0;
        int compCount = 0;
        boolean gameOver = false;
        while (!gameOver) {
            do {
                //if games over is true
                if (gameOver) {
                    break;
                }
                //asks the player for input on one single line 
                System.out.print("Enter Shot Coordinates(0-9) \"row col\": ");
                r = in.nextInt();
                c = in.nextInt();
                while (!isTargetAvailable(r, c, playerHitMissBoard)) {
                    //if where the player shot the target isnt availale its prompts the player to shot again
                    System.out.println(red + "try again" + reset);
                    System.out.print("Enter Shot Coordinates(0-9) \"row col\": ");
                    r = in.nextInt();
                    c = in.nextInt();
                    System.out.println("You entered (" + r + "," + c + ")");
                }
                checkHitMiss("Player", "Comptuer", r, c, computerBoard, playerHitMissBoard, computerShipHits);
                //if player is correct the then the count for distroyed ships parts increase
                if (isCorrect(r, c, computerBoard)) {
                    count++;
                }
                //if the total parts distroyed is equal to the total parts the game is over
                //vise varca for computer
                if (count == totalPlayerShipParts) {
                    gameOver = true;
                    System.out.println(blue + "Player Won" + reset);
                    System.out.println(red + "Computer Lost" + reset);
                }
                displayPlayerHitMissBoard();
            } while (!gameOver && r <= 9 && c <= 9 && isCorrect(r, c, computerBoard));//the players turn continues until the player is correct, game is not over
            //and while the r and c values are within the grid 

            int compR;
            int compC;
            do {
                Random rand = new Random();
                if (gameOver) {
                    break;
                }
                compR = rand.nextInt(10);
                compC = rand.nextInt(10);
                while (!isTargetAvailable(compR, compC, playerBoard)) {
                    compR = rand.nextInt(10);
                    compC = rand.nextInt(10);
                }
                System.out.println("Computer shot location: (" + compR + "," + compC + ")");
                checkHitMiss("Computer", "Player", compR, compC, playerBoard, computerHitMissBoard, playerShipHits);

                //if the comptuer hits then it takes another turn 
                if (isCorrect(compR, compC, playerBoard)) {
                    System.out.println("Comptuer goes again");
                    compCount++;
                    compR = rand.nextInt(10);
                    compC = rand.nextInt(10);
                    while (!isTargetAvailable(compR, compC, playerBoard)) {
                        compR = rand.nextInt(10);
                        compC = rand.nextInt(10);
                    }
                    System.out.println("Computer shot location: (" + compR + "," + compC + ")");
                    checkHitMiss("Computer", "Player", compR, compC, playerBoard, computerHitMissBoard, playerShipHits);
                }

                
                
                //code for ai
                //what that code does is that if the computer hits the players board instead of picking another random location
                //it targets the area arount the first shot
                //it also checks that it is within the boards bounds
                /////////////////////////////////////////////////////////////////////////////////////////
                //the thing that doesnt work with this computer ai is that if the computer misses, 
                //the next turn it gets it doesnt know what area it was supposed to target from the prevoius shots
//                while (isCorrect(compR, compC, playerBoard)) {
//                    compCount++;
//
//                    System.out.println("Computer goes again");
//                    //saves the computer shot
//                    int lastRow = compR;
//                    int lastCol = compC;
//                    //picks a direction randomly for the ai to shot
//                    int direction = rand.nextInt(4);
//                    if (compR + 1 <= 9 && compR - 1 >= 0 && compC + 1 <= 9 && compC - 1 >= 0) {
//                        switch (direction) {
//                            //right
//                            case 0:
//                                compC = lastCol + 1;
//                                compR = lastRow;
//                                break;
//                            //left  
//                            case 1:
//                                compC = lastCol - 1;
//                                compR = lastRow;
//                                break;
//                            //up    
//                            case 2:
//                                compR = lastRow - 1;
//                                compC = lastCol;
//                                break;
//                            //down
//                            case 3:
//                                compR = lastRow + 1;
//                                compC = lastCol;
//                                break;
//                        }
//                    }
//
//                    // comp shot is at the bottom
//                    if (compR + 1 <= 9 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(3);
//                        switch (direction) {
//                            //Right
//                            case 0:
//                                compC = lastCol + 1;
//                                compR = lastRow;
//                                break;
//                            //left  
//                            case 1:
//                                compC = lastCol - 1;
//                                compR = lastRow;
//                                break;
//                            //up    
//                            case 2:
//                                compR = lastRow - 1;
//                                compC = lastCol;
//                                break;
//                        }
//                    } // comp shot is at the top
//                    else if (compR - 1 >= 0 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(3);
//                        switch (direction) {
//                            //Right
//                            case 0:
//                                compC = lastCol + 1;
//                                compR = lastRow;
//                                break;
//                            //left  
//                            case 1:
//                                compC = lastCol - 1;
//                                compR = lastRow;
//                                break;
//                            //down
//                            case 2:
//                                compR = lastRow + 1;
//                                compC = lastCol;
//                                break;
//                        }
//                    } // comp shot is at the right
//                    else if (compC + 1 <= 9 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(3);
//                        switch (direction) {
//                            //Down
//                            case 0:
//                                compR = lastRow + 1;
//                                compC = lastCol;
//                                break;
//                            //left  
//                            case 1:
//                                compC = lastCol - 1;
//                                compR = lastRow;
//                                break;
//                            //up    
//                            case 2:
//                                compR = lastRow - 1;
//                                compC = lastCol;
//                                break;
//                        }
//                    } // comp shot is at the right
//                    else if (compC - 1 >= 0 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(3);
//                        switch (direction) {
//                            //Down
//                            case 0:
//                                compR = lastRow + 1;
//                                compC = lastCol;
//                                break;
//                            //right  
//                            case 1:
//                                compC = lastCol + 1;
//                                compR = lastRow;
//                                break;
//                            //up    
//                            case 2:
//                                compR = lastRow - 1;
//                                compC = lastCol;
//                                break;
//                        }
//                    } //top left corner
//                    else if (compC - 1 >= 0 && compR - 1 >= 0 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(2);
//                        switch (direction) {
//                            //Down
//                            case 0:
//                                compR = lastRow + 1;
//                                compC = lastCol;
//                                break;
//                            //right  
//                            case 1:
//                                compC = lastCol + 1;
//                                compR = lastRow;
//                                break;
//                        }
//                    } //top right corner
//                    else if (compC + 1 <= 9 && compR - 1 <= 0 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(2);
//                        switch (direction) {
//                            //Down
//                            case 0:
//                                compR = lastRow + 1;
//                                compC = lastCol;
//                                break;
//                            //left  
//                            case 1:
//                                compC = lastCol - 1;
//                                compR = lastRow;
//                                break;
//                        }
//                    } //bottom left corner
//                    else if (compC - 1 >= 0 && compR + 1 <= 9 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(2);
//                        switch (direction) {
//                            //up
//                            case 0:
//                                compR = lastRow - 1;
//                                compC = lastCol;
//                                break;
//                            //Right  
//                            case 1:
//                                compC = lastCol + 1;
//                                compR = lastRow;
//                                break;
//                        }
//                    } //bottom right corner
//                    else if (compC + 1 <= 9 && compR + 1 <= 9 || !isTargetAvailable(compR, compC, computerHitMissBoard)) {
//                        direction = rand.nextInt(2);
//                        switch (direction) {
//                            //up
//                            case 0:
//                                compR = lastRow - 1;
//                                compC = lastCol;
//                                break;
//                            //left  
//                            case 1:
//                                compC = lastCol - 1;
//                                compR = lastRow;
//                                break;
//                        }
//
//                    }
//
//                    System.out.println("Computer shot location: (" + compR + "," + compC + ")");
//                    checkHitMiss("Computer", "Player", compR, compC, playerBoard, computerHitMissBoard, playerShipHits);
//                }
                System.out.println("Compcount " + compCount);
                //same as player if the computer count reaches the same amount as the total ship parts 
                if (compCount == totalPlayerShipParts) {
                    gameOver = true;
                    System.out.println(blue + "Computer Won" + reset);
                    System.out.println(red + "Player Lost" + reset);
                }

                displayComputerHitMissBoard();

            } while (!gameOver && isCorrect(compR, compC, playerBoard));
        }
    }
    /////////////////////////////////////////////////////////////////////////

    private void placePlayerShips() {
        Scanner in = new Scanner(System.in);
        int rotation;
        int r;
        int c;
        for (int i = 0; i < shipLength.length; i++) {
            do {
                //ask the player where they want to place the player ships
                System.out.println("Where do you want to place ship " + (i + 1) + " with length: " + shipLength[i]);
                System.out.print("Rotaion:(0=Horizontal 1=Vertical)");//rotation
                rotation = in.nextInt();
                //input validation
                while (rotation > 1) {
                    System.out.println(red + "Invalid Number" + reset);
                    System.out.print("Rotaion:(0=Horizontal 1=Vertical)");
                    rotation = in.nextInt();
                }
                System.out.print("Enter Coordinates \"row col\" between(0-9): ");
                r = in.nextInt();
                c = in.nextInt();
                System.out.println("You entered (" + r + "," + c + ")");
                while(r > 9 || c > 9) {
                    System.out.println("Invalid input");
                    System.out.print("Enter Coordinates \"row col\" between(0-9): ");
                    r = in.nextInt();
                    c = in.nextInt();
                }
            } while (!isPlayerAvailable(r, c, shipLength[i], rotation));

            //horizontal
            if (rotation == 0) {
                for (int j = c; j < c + shipLength[i]; j++) {
                    playerBoard.getGrid()[r][j] = i + 1;
                }
            }
            //vertical
            if (rotation == 1) {
                for (int j = r; j < r + shipLength[i]; j++) {
                    playerBoard.getGrid()[j][c] = i + 1;
                }
            }
        }

    }

    //the code is same as player but computer chooses random locations
    private void placeComputerShips() {
        Random rand = new Random();
        int r = 0;
        int c = 0;
        int rotation = 0;
        for (int i = 0; i < shipLength.length; i++) {
            do {
                rotation = rand.nextInt(2);
                if (rotation == 0) {
                    r = rand.nextInt(computerBoard.getGrid().length);
                    c = rand.nextInt(9 - shipLength[i]);
                } else if (rotation == 1) {
                    r = rand.nextInt(9 - shipLength[i]);
                    c = rand.nextInt(computerBoard.getGrid()[0].length);
                }
            } while (!isAvailable(r, c, shipLength[i], rotation));

            if (rotation == 0) {
                for (int j = c; j < c + shipLength[i]; j++) {
                    computerBoard.getGrid()[r][j] = i + 1;
                }
            } else if (rotation == 1) {
                for (int j = r; j < r + shipLength[i]; j++) {
                    computerBoard.getGrid()[j][c] = i + 1;
                }
            }
        }
    }

    //this method checks the computer is not overlapping other ships
    private boolean isAvailable(int r, int c, int shipLength, int rotation) {
        if (rotation == 0) {  // Horizontal
            if (c + shipLength > computerBoard.getGrid()[0].length) {
                return false;
            }
            // Check for overlap with other computer ships
            for (int i = 0; i < shipLength; i++) {
                if (computerBoard.getGrid()[r][c + i] != 0) {
                    return false;
                }
            }
        } else if (rotation == 1) { //vertical
            if (r + shipLength > computerBoard.getGrid().length) {
                return false;
            }
            // Check for overlap with other computer ships
            for (int i = 0; i < shipLength; i++) {
                if (computerBoard.getGrid()[r + i][c] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //checks for player overlap
    private boolean isPlayerAvailable(int r, int c, int shipLength, int rotation) {
        if (rotation == 0) {
            for (int i = c; i < c + shipLength; i++) {
                if (playerBoard.getGrid()[r][i] >= 1) {
                    return false;
                }
            }
        } else if (rotation == 1) {
            for (int i = r; i < r + shipLength; i++) {
                if (playerBoard.getGrid()[i][c] >= 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
