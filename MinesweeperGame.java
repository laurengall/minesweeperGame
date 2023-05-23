import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {
    private static final int BOARD_SIZE = 10; // Change the board size as needed
    private static final int NUM_MINES = 20; // Change the number of mines as needed

    private char[][] board;
    private boolean[][] revealed;
    private boolean[][] flagged;
    private int numMinesRemaining;

    public MinesweeperGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        revealed = new boolean[BOARD_SIZE][BOARD_SIZE];
        revealed[4][4] = true; 
        revealed[4][5] = true; 
        revealed[5][4] = true; 
        revealed[5][5] = true; 
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
        if (r == 4 || r == 5){
          if (c == 4 || c == 5){
            board[r-3][c+3] = 'X'; 
          }
        }
        else {
          board[r][c] = 'X'; 
        }
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
      if(row != 0 && row != 9 && col != 0 && col != 9){
          if (board[row--][col] != 'X' && board[row++][col]               != 'X' && board[row][col++] != 'X' &&                       board[row][col--] != 'X'){
        revealed[row--][col] = true; 
        revealed[row++][col] = true; 
        revealed[row][col--] = true; 
        revealed[row][col++] = true; 
      }
        }
      
    }

    public void flagCell(int row, int col) {
      flagged[row][col] = true;
      if (board[row][col] == 'X'){
        numMinesRemaining--;
      } else {
        System.out.println("Hmm..."); 
      }
    }

   public void playGame() {
    Scanner scanner = new Scanner(System.in);
    boolean gameOver = false;

    while (!gameOver) {
        // Print the current board
        printBoard();


      System.out.println("\nNumber of unflagged mines: " + numMinesRemaining);
      
        // Prompt the user for input
      
        System.out.print("Enter row and column (e.g., 1 1): ");
       
        int row = scanner.nextInt() -1;
        int col = scanner.nextInt() -1;

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
    System.out.println("   1 2 3 4 5 6 7 8 9 10");
    for (int row = 1; row < BOARD_SIZE +1; row++) {
      if (row < 10){
        System.out.print(row + "  ");
      } else {
        System.out.print(row +" ");
      }
        
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (revealed[row -1][col]) {
                System.out.print(board[row -1][col] + " ");
            } else if (flagged[row-1][col]) {
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
  
 if (numMinesRemaining == 0){
    
      return true;
    
  }
    return false; 
}

    
}
