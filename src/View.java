public abstract class View {
    protected final Model model;

    protected View(Model model) {
        this.model = model;
    }

    public abstract void showBoard();

    public abstract void promptForMove(char player);

    public abstract void showInvalidCoordinate();

    public abstract void showOccupiedCell();

    public abstract void showWinner(char player);

    public abstract void showDraw();
}
