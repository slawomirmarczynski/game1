import java.io.PrintStream;

public class ConsoleView extends View {
    private final PrintStream output;

    public ConsoleView(PrintStream output) {
        this.output = output;
    }

    @Override
    public void showBoard(Model model) {
        output.println();
        output.println("   A   B   C");
        for (int row = 0; row < 3; row++) {
            output.print(row + 1);
            output.print("  ");
            for (int column = 0; column < 3; column++) {
                output.print(model.getCell(row, column));
                if (column < 2) {
                    output.print(" | ");
                }
            }
            output.println();
            if (row < 2) {
                output.println("  ---+---+---");
            }
        }
        output.println();
    }

    @Override
    public void promptForMove(char player) {
        output.print("Ruch gracza " + player + " (np. A1): ");
    }

    @Override
    public void showInvalidCoordinate() {
        output.println("Nieprawidłowe pole. Podaj kolumnę A-C i wiersz 1-3, np. A1.");
    }

    @Override
    public void showOccupiedCell() {
        output.println("To pole jest juz zajęte. Wybierz inne.");
    }

    @Override
    public void showWinner(char player) {
        output.println("Wygrywa gracz " + player + "!");
    }

    @Override
    public void showDraw() {
        output.println("Remis!");
    }
}
