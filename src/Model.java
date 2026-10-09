import java.util.Arrays;

/**
 * Przechowuje stan gry i odpowiada za walidację oraz skutki ruchów.
 * Plansza jest kwadratowa, a jej rozmiar ustala się podczas tworzenia modelu.
 */
public class Model {
    /** Znak oznaczający puste pole planszy. */
    private static final char EMPTY_CELL = ' ';

    /** Znaki używane do oznaczenia graczy. */
    private static final char FIRST_PLAYER = 'X';
    private static final char SECOND_PLAYER = 'O';

    /** Plansza gry; pierwszy indeks oznacza wiersz, a drugi kolumnę. */
    private final char[][] board;

    /** Symbol gracza, którego kolej przypada obecnie. */
    private char currentPlayer = FIRST_PLAYER;

    /** Symbol zwycięzcy; wartość EMPTY_CELL oznacza brak zwycięzcy. */
    private char winner = EMPTY_CELL;

    /** Liczba poprawnie wykonanych ruchów, używana do wykrywania remisu. */
    private long movesPlayed;

    /**
     * Tworzy pustą planszę o podanym rozmiarze.
     *
     * @param boardSize liczba wierszy i kolumn; musi być większa od zera
     * @throws IllegalArgumentException gdy rozmiar planszy nie jest dodatni
     */
    public Model(int boardSize) {
        if (boardSize <= 0) {
            throw new IllegalArgumentException("Rozmiar planszy musi być dodatni.");
        }

        board = new char[boardSize][boardSize];
        for (char[] row : board) {
            Arrays.fill(row, EMPTY_CELL);
        }
    }

    /** Zwraca liczbę wierszy i kolumn planszy. */
    public int getBoardSize() {
        return board.length;
    }

    /**
     * Zwraca zawartość wskazanego pola.
     *
     * @param row indeks wiersza liczony od zera
     * @param column indeks kolumny liczony od zera
     */
    public char getCell(int row, int column) {
        return board[row][column];
    }

    /** Zwraca symbol gracza, który ma wykonać następny ruch. */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Zwraca symbol zwycięzcy albo {@code ' '}, jeśli gra nie ma jeszcze zwycięzcy.
     */
    public char getWinner() {
        return winner;
    }

    /** Sprawdza, czy plansza została zapełniona bez uzyskania zwycięskiej linii. */
    public boolean isDraw() {
        return winner == EMPTY_CELL && movesPlayed == (long) board.length * board.length;
    }

    /** Sprawdza, czy gra zakończyła się zwycięstwem lub remisem. */
    public boolean isGameOver() {
        return winner != EMPTY_CELL || isDraw();
    }

    /**
     * Sprawdza, czy wskazane pole nie zawiera jeszcze symbolu żadnego gracza.
     *
     * @param row indeks wiersza liczony od zera
     * @param column indeks kolumny liczony od zera
     */
    public boolean isCellEmpty(int row, int column) {
        return board[row][column] == EMPTY_CELL;
    }

    /**
     * Próbuje umieścić symbol bieżącego gracza we wskazanym polu.
     * Po poprawnym ruchu sprawdza zwycięstwo, a jeśli go nie ma,
     * przekazuje kolej ruchu drugiemu graczowi.
     *
     * @param row indeks wiersza liczony od zera
     * @param column indeks kolumny liczony od zera
     * @return {@code true}, jeśli ruch został wykonany; w przeciwnym razie {@code false}
     */
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
            currentPlayer = currentPlayer == FIRST_PLAYER ? SECOND_PLAYER : FIRST_PLAYER;
        }

        return true;
    }

    /**
     * Sprawdza linie, które mogły zostać uzupełnione ostatnim ruchem.
     * Wystarczy zweryfikować jego wiersz, kolumnę i przekątne przechodzące
     * przez pole, ponieważ wcześniejsze ruchy nie zmieniły innych linii.
     */
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
