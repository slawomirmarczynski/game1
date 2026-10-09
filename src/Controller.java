import java.util.Scanner;

public class Controller {
    private final Model model;
    private final View view;
    private final Scanner input = new Scanner(System.in);

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

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

    private int[] parseCoordinate(String coordinate) {
        String normalized = coordinate.trim().toUpperCase();
        int rowStart = 0;
        while (rowStart < normalized.length()
                && normalized.charAt(rowStart) >= 'A' && normalized.charAt(rowStart) <= 'Z') {
            rowStart++;
        }
        if (rowStart == 0 || rowStart == normalized.length()) {
            return null;
        }

        long column = 0;
        for (int index = 0; index < rowStart; index++) {
            column = column * 26 + normalized.charAt(index) - 'A' + 1;
            if (column > model.getBoardSize()) {
                return null;
            }
        }

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
