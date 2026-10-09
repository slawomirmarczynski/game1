public class ConsoleView extends View {
    public ConsoleView(Model model) {
        super(model);
    }

    @Override
    public void showBoard() {
        System.out.println();
        System.out.println("   A   B   C");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + 1);
            System.out.print("  ");
            for (int column = 0; column < 3; column++) {
                System.out.print(model.getCell(row, column));
                if (column < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("  ---+---+---");
            }
        }
        System.out.println();
    }

    @Override
    public void promptForMove(char player) {
        System.out.print("Ruch gracza " + player + " (np. A1): ");
    }

    @Override
    public void showInvalidCoordinate() {
        System.out.println("Nieprawidłowe pole. Podaj kolumnę A-C i wiersz 1-3, np. A1.");
    }

    @Override
    public void showOccupiedCell() {
        System.out.println("To pole jest juz zajęte. Wybierz inne.");
    }

    @Override
    public void showWinner(char player) {
        System.out.println("Wygrywa gracz " + player + "!");
    }

    @Override
    public void showDraw() {
        System.out.println("Remis!");
    }
}
