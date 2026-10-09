/** Punkt wejścia i główna pętla rozgrywki. */
public class Program implements Runnable {
    /**
     * Tworzy model, widok i kontroler lokalnie oraz prowadzi grę do jej zakończenia.
     * Lokalny czas życia tych obiektów ogranicza ich stan do pojedynczego uruchomienia.
     */
    @Override
    public void run() {
        final int boardSize = 6;
        Model model = new Model(boardSize);
        View view = new ConsoleView(model);
        Controller controller = new Controller(model, view);

        view.showBoard();

        while (!model.isGameOver()) {
            if (!controller.playTurn()) {
                return;
            }
            view.showBoard();
        }

        if (model.isDraw()) {
            view.showDraw();
        } else {
            view.showWinner(model.getWinner());
        }
    }

    /** Uruchamia program konsolowy. */
    public static void main(String[] args) {
        new Program().run();
    }
}
