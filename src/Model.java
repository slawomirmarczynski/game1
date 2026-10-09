public class Model {
    private static final int BOARD_SIZE = 3;

    private final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private char currentPlayer = 'X';
    private char winner = ' ';
    private int movesPlayed;

    public Model() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                board[row][column] = ' ';
            }
        }
    }

    public char getCell(int row, int column) {
        return board[row][column];
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getWinner() {
        return winner;
    }

    public boolean isDraw() {
        return winner == ' ' && movesPlayed == BOARD_SIZE * BOARD_SIZE;
    }

    public boolean isGameOver() {
        return winner != ' ' || isDraw();
    }

    public boolean isCellEmpty(int row, int column) {
        return board[row][column] == ' ';
    }

    public boolean makeMove(int row, int column) {
        if (row < 0 || row >= BOARD_SIZE || column < 0 || column >= BOARD_SIZE
                || isGameOver() || !isCellEmpty(row, column)) {
            return false;
        }

        board[row][column] = currentPlayer;
        movesPlayed++;

        if (hasWon(row, column)) {
            winner = currentPlayer;
        } else {
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }

        return true;
    }

    private boolean hasWon(int row, int column) {
        return board[row][0] == currentPlayer
                && board[row][1] == currentPlayer
                && board[row][2] == currentPlayer
                || board[0][column] == currentPlayer
                && board[1][column] == currentPlayer
                && board[2][column] == currentPlayer
                || row == column
                && board[0][0] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][2] == currentPlayer
                || row + column == BOARD_SIZE - 1
                && board[0][2] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][0] == currentPlayer;
    }
}
