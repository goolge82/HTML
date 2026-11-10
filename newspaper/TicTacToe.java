
import java.util.Scanner;

public class TicTacToe {

    static Scanner sc = new Scanner(System.in);

    static char[][] board = new char[3][3];

    static char player = 'x';

    static int moves = 0;

    // ANSI Colors
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[91m";
    static final String GREEN = "\u001B[92m";
    static final String YELLOW = "\u001B[93m";
    static final String BLUE = "\u001B[94m";
    static final String PURPLE = "\u001B[95m";
    static final String CYAN = "\u001B[96m";
    static final String WHITE = "\u001B[97m";
    static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {

        printWelcome();

        // Initialize board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        while (true) {

            printBoard();
            printPlayerTurn();

            System.out.print(CYAN + "  Enter row    : " + RESET);
            int row = sc.nextInt();

            System.out.print(CYAN + "  Enter column : " + RESET);
            int col = sc.nextInt();

            // Validate move
            if (row < 0 || row > 2 ||
                    col < 0 || col > 2 ||
                    board[row][col] != ' ') {

                System.out.println();
                System.out.println(RED + BOLD +
                        "  [!] INVALID MOVE! TRY AGAIN!" + RESET);

                continue;
            }

            // Place symbol
            board[row][col] = player;
            moves++;

            // Check winner
            if (checkWinner()) {

                printBoard();
                printWinner();

                break;
            }

            // Check draw
            if (isBoardFull()) {

                printBoard();

                System.out.println(YELLOW + BOLD);
                System.out.println("  ╔══════════════════════════════╗");
                System.out.println("  ║                              ║");
                System.out.println("  ║         MATCH DRAW!          ║");
                System.out.println("  ║                              ║");
                System.out.println("  ║      WELL PLAYED BOTH!       ║");
                System.out.println("  ║                              ║");
                System.out.println("  ╚══════════════════════════════╝");
                System.out.println(RESET);

                break;
            }

            // Switch player
            player = (player == 'x') ? 'o' : 'x';
        }

        System.out.println(PURPLE + BOLD);
        System.out.println("  ╔══════════════════════════════╗");
        System.out.println("  ║       GAME OVER              ║");
        System.out.println("  ║     THANK YOU FOR PLAYING!   ║");
        System.out.println("  ╚══════════════════════════════╝");
        System.out.println(RESET);

        sc.close();
    }

    // Welcome screen
    public static void printWelcome() {

        System.out.println(PURPLE + BOLD);

        System.out.println();
        System.out.println("  ██████╗ ██╗ ██████╗");
        System.out.println("  ██╔══██╗██║██╔════╝");
        System.out.println("  ██║  ██║██║██║");
        System.out.println("  ██║  ██║██║██║");
        System.out.println("  ██████╔╝██║╚██████╗");
        System.out.println("  ╚═════╝ ╚═╝ ╚═════╝");

        System.out.println();
        System.out.println("  ████████╗ █████╗  ██████╗");
        System.out.println("  ╚══██╔══╝██╔══██╗██╔════╝");
        System.out.println("     ██║   ███████║██║");
        System.out.println("     ██║   ██╔══██║██║");
        System.out.println("     ██║   ██║  ██║╚██████╗");
        System.out.println("     ╚═╝   ╚═╝  ╚═╝ ╚═════╝");

        System.out.println(RESET);

        System.out.println(CYAN + BOLD);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║       VCUBE ARCADE CENTER        ║");
        System.out.println("  ║                                  ║");
        System.out.println("  ║          TIC TAC TOE             ║");
        System.out.println("  ║                                  ║");
        System.out.println("  ║       THE ULTIMATE BATTLE        ║");
        System.out.println("  ╚══════════════════════════════════╝");
        System.out.println(RESET);

        System.out.println();
        System.out.println(GREEN + BOLD +
                "       PLAYER X  " + RESET +
                YELLOW + " VS " + RESET +
                RED + BOLD + "  PLAYER O" + RESET);

        System.out.println();
        System.out.println(WHITE +
                "  HOW TO PLAY: Enter row and column (0-2)" +
                RESET);

        System.out.println();
        System.out.println(YELLOW +
                "  Starting game... LET'S PLAY!" + RESET);
    }

    // Player turn display
    public static void printPlayerTurn() {

        System.out.println();

        System.out.println(CYAN +
                "  ╔══════════════════════════════════╗");

        if (player == 'x') {

            System.out.println(GREEN + BOLD +
                    "  ║       PLAYER X  - YOUR TURN      ║");
        } else {

            System.out.println(RED + BOLD +
                    "  ║       PLAYER O  - YOUR TURN      ║");
        }

        System.out.println(CYAN +
                "  ║                                  ║");

        System.out.println(CYAN +
                "  ║       MOVE NUMBER : " + moves +
                "                 ║");

        System.out.println(CYAN +
                "  ╚══════════════════════════════════╝" +
                RESET);

    }

    // Beautiful game board
    public static void printBoard() {

        System.out.println();

        System.out.println(PURPLE + BOLD +
                "              GAME ARENA" + RESET);

        System.out.println();

        System.out.println("              0       1       2");

        System.out.println(CYAN +
                "          ╔═══════╦═══════╦═══════╗");

        for (int i = 0; i < 3; i++) {

            System.out.print("       " + YELLOW + i + RESET +
                    CYAN + "  ║");

            for (int j = 0; j < 3; j++) {

                char symbol = board[i][j];

                if (symbol == 'x') {

                    System.out.print("   " + GREEN +
                            BOLD + "X" + RESET +
                            CYAN + "   ║");
                } else if (symbol == 'o') {

                    System.out.print("   " + RED +
                            BOLD + "O" + RESET +
                            CYAN + "   ║");
                } else {

                    System.out.print("       ║");
                }
            }

            System.out.println();

            if (i < 2) {

                System.out.println(CYAN +
                        "          ╠═══════╬═══════╬═══════╣");
            }
        }

        System.out.println(CYAN +
                "          ╚═══════╩═══════╩═══════╝" +
                RESET);

        System.out.println();
        System.out.println(GREEN + "     X" + RESET +
                " = Player X       " +
                RED + "O" + RESET + " = Player O");

    }

    // Check winner
    public static boolean checkWinner() {

        // Rows
        for (int i = 0; i < 3; i++) {

            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {

                return true;
            }
        }

        // Columns
        for (int i = 0; i < 3; i++) {

            if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player) {

                return true;
            }
        }

        // First diagonal
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {

            return true;
        }

        // Second diagonal
        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {

            return true;
        }

        return false;
    }

    // Check board full
    public static boolean isBoardFull() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    // Winner celebration
    public static void printWinner() {

        System.out.println();

        String color = (player == 'x') ? GREEN : RED;

        System.out.println(color + BOLD);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║                                  ║");
        System.out.println("  ║       ★ CONGRATULATIONS ★        ║");
        System.out.println("  ║                                  ║");
        System.out.println("  ║         PLAYER " + player +
                " WINS!             ║");
        System.out.println("  ║                                  ║");
        System.out.println("  ║        YOU ARE THE CHAMPION!     ║");
        System.out.println("  ║                                  ║");
        System.out.println("  ╚══════════════════════════════════╝");
        System.out.println(RESET);
    }
}
