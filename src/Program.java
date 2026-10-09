import java.util.Scanner;

public class Program implements Runnable {
    private final Model model;
    private final View view;
    private final Controller controller;

    public Program() {
        model = new Model();
        view = new View(System.out);
        controller = new Controller(model, view, new Scanner(System.in));
    }

    @Override
    public void run() {
        view.showBoard(model);
        while (!model.isGameOver()) {
            if (!controller.playTurn()) {
                return;
            }
            view.showBoard(model);
        }

        if (model.getWinner() != ' ') {
            view.showWinner(model.getWinner());
        } else {
            view.showDraw();
        }
    }

    public static void main(String[] args) {
        new Program().run();
    }
}
