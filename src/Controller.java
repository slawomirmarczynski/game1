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
        if (normalized.length() != 2) {
            return null;
        }

        char column = normalized.charAt(0);
        char row = normalized.charAt(1);
        if (column < 'A' || column > 'C' || row < '1' || row > '3') {
            return null;
        }

        return new int[]{row - '1', column - 'A'};
    }
}
