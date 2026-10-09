public class Model {
    private final char[][] board;
    private char currentPlayer = 'X';
    private char winner = ' ';
    private long movesPlayed = 0;

    public Model(int boardSize) {
        if (boardSize <= 0) {
            throw new IllegalArgumentException("Board size must be positive.");
        }

        board = new char[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                board[row][column] = ' ';
            }
        }
    }

    public int getBoardSize() {
        return board.length;
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
        return winner == ' ' && movesPlayed == (long) board.length * board.length;
    }

    public boolean isGameOver() {
        return winner != ' ' || isDraw();
    }

    public boolean isCellEmpty(int row, int column) {
        return board[row][column] == ' ';
    }

    public boolean makeMove(int row, int column) {
        if (row < 0 || row >= board.length || column < 0 || column >= board.length
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
        boolean completeRow = true;
        boolean completeColumn = true;
        boolean completeDiagonal = row == column;
        boolean completeAntiDiagonal = row + column == board.length - 1;

        for (int index = 0; index < board.length; index++) {
            completeRow &= board[row][index] == currentPlayer;
            completeColumn &= board[index][column] == currentPlayer;
            if (completeDiagonal) {
                completeDiagonal &= board[index][index] == currentPlayer;
            }
            if (completeAntiDiagonal) {
                completeAntiDiagonal &= board[index][board.length - 1 - index] == currentPlayer;
            }
        }

        return completeRow || completeColumn || completeDiagonal || completeAntiDiagonal;
    }
}
