/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 309954
 */
public class Board {

    int[][] grid;
    
    public Board() {
        grid = new int[10][10];
    }

    public Board(int row, int col) {
        grid = new int[row][col];
    }

    public void display(int index) {
        for (int r = 0; r < getGrid().length; r++) {
            System.out.print(grid[index][r]);
            System.out.print(" ");
        }
    }

    public int[][] getGrid() {
        return grid;
    }
}
