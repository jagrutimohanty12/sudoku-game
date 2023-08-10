import java.util.Scanner;

public class SudokuGame {
    private static final int BOARD_SIZE = 9;
    private static final int EMPTY_CELL = 0;

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Initial Sudoku Board:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku Board:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the Sudoku puzzle.");
        }
    }

    private static boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);

        if (emptyCell == null) {
            return true; // No empty cells left; the puzzle is solved.
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int num = 1; num <= BOARD_SIZE; num++) {
            if (isValidMove(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                board[row][col] = EMPTY_CELL; // Backtrack if the current num doesn't lead to a solution.
            }
        }

        return false; // No valid number for the current cell; backtrack to a previous state.
    }

    private static boolean isValidMove(int[][] board, int row, int col, int num) {
        return isNumNotInRow(board, row, num) &&
               isNumNotInColumn(board, col, num) &&
               isNumNotInBox(board, row - row % 3, col - col % 3, num);
    }

    private static boolean isNumNotInRow(int[][] board, int row, int num) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumNotInColumn(int[][] board, int col, int num) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumNotInBox(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] findEmptyCell(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return new int[] { row, col };
                }
            }
        }
        return null;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}