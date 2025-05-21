/**
 * Käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;

    /**
     * Controlleri konstruktor
     * @param model App failius loodud mudel
     * @param view App failis loodud view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

    }

    /**
     * käivitab mängu loogika
     */
    public void start() {
        boolean running = true;
        while (running) {
            view.showMenu();
            int choice = view.getMenuChoice(); // Küsi kasutajalt menüü valikut
            switch (choice) {
                case 1:
                    model.initGame(); // loo uus mäng
                    //view.showMessage(String.valueOf(model.getPc_number())); //TEST, mida arvuti mõtles
                    Stopwach stopwach = new Stopwach(); //loome stopperi
                    stopwach.start(); //käivitame stopperi
                    while (!model.isGame_over()) { //Kui mäng ei ole läbi
                        int guess = view.askGuess();// Küsi kasutajalt numbrit
                        view.showMessage(model.checkGuess(guess)); // Kontrolli ja väljasta tulemus
                    }

                    stopwach.stop();// peata stopper



                    view.showMessage("Mängu aeg: " + stopwach.getElapsedTime() + " (" + stopwach.getElapsedMillis() + ")");
                    String name = view.askName();
                    model.saveScore(name);

                    break;
                case 2:
                    view.showScoreboard(model.loadScores());
                    break;

                case 3:
                    running = false;
                    view.showMessage("Head aega!");
                    break;
                default:
                    view.showMessage("Vigane valik!");
            }
        }

    }

}
