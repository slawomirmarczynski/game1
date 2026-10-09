public abstract class View {
    public abstract void showBoard(Model model);

    public abstract void promptForMove(char player);

    public abstract void showInvalidCoordinate();

    public abstract void showOccupiedCell();

    public abstract void showWinner(char player);

    public abstract void showDraw();
}
