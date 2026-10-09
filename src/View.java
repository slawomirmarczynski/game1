import java.util.Objects;

/**
 * Abstrakcyjny interfejs widoku, który prezentuje stan gry użytkownikowi.
 * Model jest powiązany z widokiem przy jego tworzeniu.
 */
public abstract class View {
    /** Model, którego stan widok wyświetla. */
    protected final Model model;

    /**
     * Tworzy widok powiązany z podanym modelem.
     *
     * @param model model gry prezentowany przez widok
     * @throws NullPointerException jeśli model ma wartość {@code null}
     */
    protected View(Model model) {
        this.model = Objects.requireNonNull(model, "Model nie może być null.");
    }

    /** Wyświetla aktualny stan planszy. */
    public abstract void showBoard();

    /** Prosi wskazanego gracza o podanie ruchu. */
    public abstract void promptForMove(char player);

    /** Informuje o błędnym formacie lub zakresie podanej współrzędnej. */
    public abstract void showInvalidCoordinate();

    /** Informuje, że wybrane pole jest już zajęte. */
    public abstract void showOccupiedCell();

    /** Wyświetla informację o zwycięstwie wskazanego gracza. */
    public abstract void showWinner(char player);

    /** Wyświetla informację o zakończeniu gry remisem. */
    public abstract void showDraw();
}
