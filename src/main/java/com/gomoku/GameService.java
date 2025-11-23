package com.gomoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service class that manages the game logic for a Go-Moku game.
 * Handles game state, moves, and win conditions.
 * 
 * @author Khaleel Alabbadi
 */
public class GameService {
    private final char[][] board;
    private final int size;
    private boolean playerTurn = true;
    private boolean gameOver = false;
    private String gameResult = "";
    private final Random random = new Random();
    private static final char EMPTY = '.';
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';
    private static final int WIN_CONDITION = 5;
        /**
     * Constructs a new GameService with the specified board size.
     *
     * @param size the size of the game board (size x size)
     * @author Khaleel Alabbadi
     */
    public GameService(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
    }
        /**
     * Initializes the game board with empty cells.
     * 
     * @author Khaleel Alabbadi
     */
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
        /**
     * Attempts to make a move on the game board.
     *
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @param player the player making the move ('X' or 'O')
     * @return true if the move was valid and made, false otherwise
     * @author Khaleel Alabbadi
     */
    public boolean makeMove(int row, int col, char player) {
        if (!isValidMove(row, col) || player != (playerTurn ? PLAYER : COMPUTER)) {
            return false;
        }
        board[row][col] = player;
        checkGameOver(row, col, player);
        if (!gameOver) {
            playerTurn = !playerTurn;
        }
        return true;
    }
        /**
     * Makes a move for the computer player.
     * Currently selects a random available move.
     * 
     * @author Khaleel Alabbadi
     */
    public void makeComputerMove() {
        List<int[]> availableMoves = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == EMPTY) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }
        if (!availableMoves.isEmpty()) {
            int[] move = availableMoves.get(random.nextInt(availableMoves.size()));
            makeMove(move[0], move[1], COMPUTER);
        }
    }
        /**
     * Checks if a move is valid.
     *
     * @param row the row index to check
     * @param col the column index to check
     * @return true if the move is valid, false otherwise
     * @author Khaleel Alabbadi
     */
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == EMPTY;
    }
        /**
     * Checks if the game is over after a move.
     *
     * @param row the row of the last move
     * @param col the column of the last move
     * @param player the player who made the last move
     * @author Khaleel Alabbadi
     */
    private void checkGameOver(int row, int col, char player) {
        if (checkWin(row, col, player)) {
            gameOver = true;
            gameResult = player == PLAYER ? "You win!" : "Computer wins!";
        } else if (isBoardFull()) {
            gameOver = true;
            gameResult = "It's a draw!";
        }
    }
        /**
     * Checks if the last move resulted in a win.
     *
     * @param row the row of the last move
     * @param col the column of the last move
     * @param player the player who made the last move
     * @return true if the move resulted in a win, false otherwise
     * @author Khaleel Alabbadi
     */
    private boolean checkWin(int row, int col, char player) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] dir : directions) {
            if (countInDirection(row, col, dir[0], dir[1], player) >= WIN_CONDITION) {
                return true;
            }
        }
        return false;
    }
        /**
     * Counts consecutive player pieces in a specific direction.
     *
     * @param row the starting row
     * @param col the starting column
     * @param dRow the row direction (-1, 0, or 1)
     * @param dCol the column direction (-1, 0, or 1)
     * @param player the player to count pieces for
     * @return the total count of consecutive player pieces in the specified direction
     * @author Khaleel Alabbadi
     */
    private int countInDirection(int row, int col, int dRow, int dCol, char player) {
        int count = 1;
        count += countDirection(row, col, dRow, dCol, player);
        count += countDirection(row, col, -dRow, -dCol, player);
        return count;
    }
        /**
     * Helper method to count consecutive player pieces in one direction.
     *
     * @param row the starting row
     * @param col the starting column
     * @param dRow the row direction
     * @param dCol the column direction
     * @param player the player to count pieces for
     * @return the count of consecutive player pieces in the specified direction
     * @author Khaleel Alabbadi
     */
    private int countDirection(int row, int col, int dRow, int dCol, char player) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;
        while (r >= 0 && r < size && c >= 0 && c < size && board[r][c] == player) {
            count++;
            r += dRow;
            c += dCol;
        }
        return count;
    }
        /**
     * Checks if the game board is full.
     *
     * @return true if the board is full, false otherwise
     * @author Khaleel Alabbadi
     */
    private boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
        /**
     * Returns a copy of the current game board.
     *
     * @return a 2D char array representing the game board
     * @author Khaleel Alabbadi
     */
    public char[][] getBoard() {
        char[][] copy = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, size);
        }
        return copy;
    }
        /**
     * Checks if it's the player's turn.
     *
     * @return true if it's the player's turn, false if it's the computer's turn
     * @author Khaleel Alabbadi
     */
    public boolean isPlayerTurn() {
        return playerTurn;
    }
        /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     * @author Khaleel Alabbadi
     */
    public boolean isGameOver() {
        return gameOver;
    }
        /**
     * Gets the game result message.
     *
     * @return a string describing the game result
     * @author Khaleel Alabbadi
     */
    public String getGameResult() {
        return gameResult;
    }
}
