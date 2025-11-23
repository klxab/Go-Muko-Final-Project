package com.gomoku;
import java.util.Scanner;
public class GoMokuGame {
    private static final int BOARD_SIZE = 6;
    private final GameService gameService;
    private final Scanner scanner;
    public GoMokuGame() {
        this.gameService = new GameService(BOARD_SIZE);
        this.scanner = new Scanner(System.in);
    }
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
    private void computerMove() {
        System.out.println("Computer's turn...");
        gameService.makeComputerMove();
    }
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
    public static void main(String[] args) {
        new GoMokuGame().start();
    }
}
