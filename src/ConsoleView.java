public class ConsoleView extends View {
    public ConsoleView(Model model) {
        super(model);
    }

    @Override
    public void showBoard() {
        int boardSize = model.getBoardSize();
        int rowLabelWidth = Integer.toString(boardSize).length();
        int cellWidth = Math.max(rowLabelWidth, columnLabel(boardSize - 1).length());

        System.out.println();
        System.out.print(" ".repeat(rowLabelWidth + 2));
        for (int column = 0; column < boardSize; column++) {
            System.out.print(center(columnLabel(column), cellWidth));
            if (column < boardSize - 1) {
                System.out.print(" | ");
            }
        }
        System.out.println();
        for (int row = 0; row < boardSize; row++) {
            System.out.print(String.format("%" + rowLabelWidth + "d  ", row + 1));
            for (int column = 0; column < boardSize; column++) {
                System.out.print(center(String.valueOf(model.getCell(row, column)), cellWidth));
                if (column < boardSize - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < boardSize - 1) {
                System.out.print(" ".repeat(rowLabelWidth + 2));
                for (int column = 0; column < boardSize; column++) {
                    System.out.print("-".repeat(cellWidth));
                    if (column < boardSize - 1) {
                        System.out.print("-+-");
                    }
                }
                System.out.println();
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
        System.out.println("Nieprawidłowe pole. Podaj kolumnę A-" + columnLabel(model.getBoardSize() - 1)
                + " i wiersz 1-" + model.getBoardSize() + ", np. A1.");
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

    private String columnLabel(int column) {
        StringBuilder label = new StringBuilder();
        for (int value = column + 1; value > 0; value = (value - 1) / 26) {
            label.insert(0, (char) ('A' + (value - 1) % 26));
        }
        return label.toString();
    }

    private String center(String value, int width) {
        int padding = width - value.length();
        int leftPadding = padding / 2;
        return " ".repeat(leftPadding) + value + " ".repeat(padding - leftPadding);
    }
}
