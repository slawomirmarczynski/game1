public class Program implements Runnable {
    private final Model model;
    private final View view;
    private final Controller controller;

    public Program() {
        model = new Model(3);
        view = new ConsoleView(model);
        controller = new Controller(model, view);
    }

    @Override
    public void run() {
        view.showBoard();
        while (!model.isGameOver()) {
            if (!controller.playTurn()) {
                return;
            }
            view.showBoard();
        }

        if (model.getWinner() != ' ') {
            view.showWinner(model.getWinner());
        } else {
            view.showDraw();
        }
    }

    public static void main(String[] args) {
        Runnable program = new Program();
        program.run();
    }
}
