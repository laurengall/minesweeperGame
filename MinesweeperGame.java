import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {
    private static final int BOARD_SIZE = 10; // Change the board size as needed
    private static final int NUM_MINES = 10; // Change the number of mines as needed

    private char[][] board;
    private boolean[][] revealed;
    private boolean[][] flagged;
    private int numMinesRemaining;

    public MinesweeperGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        revealed = new boolean[BOARD_SIZE][BOARD_SIZE];
        flagged = new boolean[BOARD_SIZE][BOARD_SIZE];
        numMinesRemaining = NUM_MINES;

        initializeBoard();
        placeMines();
        calculateNumbers();
    }

    private void initializeBoard() {
        // Initialize the board with empty cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = '-';
            }
        }
    }

    private void placeMines() {
         // TODO: Implement the placeMines method
        // Randomly place mines on the board

      for (int i = 0; i < 20; i++){
        Random rand = new Random(); 
        int r = rand.nextInt(BOARD_SIZE -1); 
        int c = rand.nextInt(BOARD_SIZE -1); 
        board[r][c] = 'X'; 
      }
    }

    private void calculateNumbers() {
        // Calculate the numbers for each cell
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != 'X') {
                    int numMines = 0;
                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = col - 1; j <= col + 1; j++) {
                            if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j] == 'X') {
                                numMines++;
                            }
                        }
                    }
                    board[row][col] = (char) (numMines + '0');
                }
            }
        }
    }

    public void revealCell(int row, int col) {
        revealed[row][col] = true; 
    }

    public void flagCell(int row, int col) {
      flagged[row][col] = true;
      if (board[row][col] == 'X'){
        numMinesRemaining--;
      }
    }

   public void playGame() {
    Scanner scanner = new Scanner(System.in);
    boolean gameOver = false;

    while (!gameOver) {
        // Print the current board
        printBoard();

        // Prompt the user for input
        System.out.print("Enter row and column (e.g., 0 0): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        // Process user input
        if (isValidCell(row, col)) {
            String action = getAction(scanner);
            if (action.equalsIgnoreCase("R")) {
                revealCell(row, col);
                if (board[row][col] == 'X') {
                    gameOver = true;
                    System.out.println("Game Over! You hit a mine.");
                }
           } else if (action.equalsIgnoreCase("F")) {
                flagCell(row, col);
            } else {
                System.out.println("Invalid action. Please enter 'R' to reveal or 'F' to flag.");
            }
        } else {
            System.out.println("Invalid cell. Please enter valid row and column values.");
        }

        // Check for game over conditions
        if (isWinningCondition()) {
            gameOver = true;
            System.out.println("Congratulations! You win!");
        }
    }
   }
private void printBoard() {
    System.out.println("  0 1 2 3 4 5 6 7 8 9");
    for (int row = 0; row < BOARD_SIZE; row++) {
        System.out.print(row + " ");
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (revealed[row][col]) {
                System.out.print(board[row][col] + " ");
            } else if (flagged[row][col]) {
                System.out.print("F ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
    }
}

private boolean isValidCell(int row, int col) {
    boolean valid = false;
      if (row < BOARD_SIZE && col < BOARD_SIZE && row >= 0 && col >= 0){
    valid = true; 
  }
    return valid; 
}

private String getAction(Scanner scanner) {
    System.out.print("Enter 'R' to reveal or 'F' to flag: ");
    return scanner.next();
}

private boolean isWinningCondition() {
    // TODO: Implement the isValidCell method
    // Check if all non-mine cells have been revealed
  if(numMinesRemaining == 0){
    return true;
  }
    return false; 
}

    
}
