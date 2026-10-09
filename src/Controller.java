import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * Obsługuje wejście użytkownika i przekazuje poprawne ruchy do modelu.
 */
public class Controller {
    /** Model, w którym zapisywane są ruchy. */
    private final Model model;

    /** Widok używany do wyświetlania komunikatów i próśb o ruch. */
    private final View view;

    /** Czytnik wejścia konsolowego używany przez całą rozgrywkę. */
    private final Scanner input = new Scanner(System.in);

    /**
     * Tworzy kontroler powiązany z modelem i widokiem gry.
     *
     * @param model model aktualizowany przez kontroler
     * @param view widok używany do komunikacji z graczem
     * @throws NullPointerException jeśli model lub widok ma wartość {@code null}
     */
    public Controller(Model model, View view) {
        this.model = Objects.requireNonNull(model, "Model nie może być null.");
        this.view = Objects.requireNonNull(view, "Widok nie może być null.");
    }

    /**
     * Odczytuje i obsługuje ruch, ponawiając pytanie po błędnym wejściu.
     *
     * @return {@code true}, jeśli wykonano ruch; {@code false}, gdy wejście zostało zamknięte
     */
    public boolean playTurn() {
        while (true) {
            view.promptForMove(model.getCurrentPlayer());
            if (!input.hasNextLine()) {
                return false;
            }

            int[] position = parseCoordinate(input.nextLine());
            if (position == null) {
                view.showInvalidCoordinate();
                continue;
            }

            if (!model.makeMove(position[0], position[1])) {
                view.showOccupiedCell();
                continue;
            }
            return true;
        }
    }

    /**
     * Zamienia współrzędną w formacie litery kolumny i numeru wiersza
     * (na przykład {@code A1} lub {@code AA12}) na indeksy liczone od zera.
     *
     * @param coordinate tekst wpisany przez użytkownika
     * @return para indeksów {@code [wiersz, kolumna]} albo {@code null}, gdy format
     *         lub zakres współrzędnej jest nieprawidłowy
     */
    private int[] parseCoordinate(String coordinate) {
        String normalized = coordinate.trim().toUpperCase(Locale.ROOT);
        int rowStart = 0;

        // Etykieta kolumny może zawierać wiele liter, np. AA dla 27. kolumny.
        while (rowStart < normalized.length()
                && normalized.charAt(rowStart) >= 'A' && normalized.charAt(rowStart) <= 'Z') {
            rowStart++;
        }
        if (rowStart == 0 || rowStart == normalized.length()) {
            return null;
        }

        // Odczyt kolumny jako liczby o podstawie 26 i sprawdzanie zakresu w trakcie.
        long column = 0;
        for (int index = 0; index < rowStart; index++) {
            column = column * 26 + normalized.charAt(index) - 'A' + 1;
            if (column > model.getBoardSize()) {
                return null;
            }
        }

        // Numer wiersza jest zapisywany dziesiętnie i numerowany od 1.
        long row = 0;
        for (int index = rowStart; index < normalized.length(); index++) {
            char digit = normalized.charAt(index);
            if (digit < '0' || digit > '9') {
                return null;
            }
            row = row * 10 + digit - '0';
            if (row > model.getBoardSize()) {
                return null;
            }
        }
        if (row == 0) {
            return null;
        }

        return new int[]{(int) row - 1, (int) column - 1};
    }
}
