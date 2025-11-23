package com.gomoku;

import java.util.Scanner;

/**
 * Main class for the Go-Moku game that handles the game loop and user interaction.
 * 
 * @author Khaleel Alabbadi
 */
public class GoMokuGame {
    private static final int BOARD_SIZE = 6;
    private final GameService gameService;
    private final Scanner scanner;
    /**
     * Constructs a new Go-Moku game with default board size.
     */
    public GoMokuGame() {
        this.gameService = new GameService(BOARD_SIZE);
        this.scanner = new Scanner(System.in);
    }
    /**
     * Starts the Go-Moku game and manages the game loop.
     * Handles the alternation between player and computer turns until the game is over.
     */
    public void start() {
        System.out.println("Welcome to 6x6 Go-Moku!");
        System.out.println("Enter moves as 'row col' (1-6)");
        
        while (!gameService.isGameOver()) {
            printBoard();
            if (gameService.isPlayerTurn()) {
                playerMove();
            } else {
                computerMove();
            }
        }
        
        printBoard();
        System.out.println(gameService.getGameResult());
        scanner.close();
    }
    /**
     * Handles the player's move by reading input from the console.
     * Validates the input and makes the move if it's valid.
     */
    private void playerMove() {
        while (true) {
            System.out.print("Your move (row col): ");
            try {
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;
                
                if (gameService.makeMove(row, col, 'X')) {
                    return;
                }
                System.out.println("Invalid move. Try again.");
            } catch (Exception e) {
                System.out.println("Please enter two numbers between 1 and 6.");
                scanner.nextLine();
            }
        }
    }
    /**
     * Handles the computer's move by delegating to the GameService.
     * Informs the user that the computer is making a move.
     */
    private void computerMove() {
        System.out.println("Computer's turn...");
        gameService.makeComputerMove();
    }
    /**
     * Prints the current state of the game board to the console.
     * Includes row and column numbers for easier move input.
     */
    private void printBoard() {
        System.out.println("\n  1 2 3 4 5 6");
        char[][] board = gameService.getBoard();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Main method to start the Go-Moku game.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new GoMokuGame().start();
    }
}
