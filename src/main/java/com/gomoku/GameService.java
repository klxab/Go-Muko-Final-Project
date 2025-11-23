package com.gomoku;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    public GameService(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
    }
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
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
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == EMPTY;
    }
    private void checkGameOver(int row, int col, char player) {
        if (checkWin(row, col, player)) {
            gameOver = true;
            gameResult = player == PLAYER ? "You win!" : "Computer wins!";
        } else if (isBoardFull()) {
            gameOver = true;
            gameResult = "It's a draw!";
        }
    }
    private boolean checkWin(int row, int col, char player) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] dir : directions) {
            if (countInDirection(row, col, dir[0], dir[1], player) >= WIN_CONDITION) {
                return true;
            }
        }
        return false;
    }
    private int countInDirection(int row, int col, int dRow, int dCol, char player) {
        int count = 1;
        count += countDirection(row, col, dRow, dCol, player);
        count += countDirection(row, col, -dRow, -dCol, player);
        return count;
    }
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
    public char[][] getBoard() {
        char[][] copy = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, size);
        }
        return copy;
    }
    public boolean isPlayerTurn() {
        return playerTurn;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public String getGameResult() {
        return gameResult;
    }
}
