import java.util.Random;

public class GameLogic {
    private char[] board;
    private Random random;

    public GameLogic() {
        board = new char[9];
        random = new Random();
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= 9) return false;
        if (board[index] != ' ') return false;
        board[index] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        int[][] patterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] pattern : patterns) {
            if (board[pattern[0]] == symbol &&
                board[pattern[1]] == symbol &&
                board[pattern[2]] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    public int computerMove() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = 'O';
                if (checkWinner('O')) return i;
                board[i] = ' ';
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = 'X';
                if (checkWinner('X')) {
                    board[i] = 'O';
                    return i;
                }
                board[i] = ' ';
            }
        }
        if (board[4] == ' ') {
            board[4] = 'O';
            return 4;
        }
        int index;
        do {
            index = random.nextInt(9);
        } while (board[index] != ' ');
        board[index] = 'O';
        return index;
    }

    public char[] getBoard() {
        return board;
    }
}