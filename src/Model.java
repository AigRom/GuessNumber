import java.io.*;
import java.util.*;

/**
 * Kogu mängu loogika asub siin.
 */
public class Model {
    private final int MINIMUM = 1;
    private final int MAXIMUM = 100;
    private final String filename = "Scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>();

    private int pc_number; //Arvuti mõeldud number
    private int steps; //Käikude lugeja
    private boolean game_over; // Kas mäng on läbi


    /**'
     * Uuue mängu loomine
     */
    public void initGame() {
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;

    }

    //GETTERS

    /**
     * Arvuti mõeldud number
     * @return juhuslik number 1-100
     */
    public int getPc_number() {
        return pc_number;
    }

    /**
     * Tehtud sammude arv mängus
     * @return sammude arv
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Kas mäng on läbi
     * @return true on läbi, false ei ole mäng läbi
     */
    public boolean isGame_over() {
        return game_over;
    }

    /**
     * Kontrollib kasutaja sisestust ja tagastab teksti
     * @param guess number mida kontrollida
     * @return tekst mida näidatakse kasutajale
     */
    public String checkGuess(int guess) {
        steps++;
        if (guess == pc_number) {
            game_over = true;
            return "Sa võitsid " + steps + " sammuga.";
        } else if(guess< pc_number) {
            return "Liiga väike";
        } else {
            return "Liiga suur";
        }
    }

    /**
     * Salvestab listisisu (edetabeli) uuesti faili (kirjutab üle)
     * @param  name faili üle kirjutamine
     */
    public void saveScore(String name) {
        loadScores(); //laeb failst sisu listi
        scoreboard.add(new Content(name, steps)); // Lisa nimi ja sammude arv LISTI
        Collections.sort(scoreboard); //Sorteerib listi
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps()); //Semikoolon juttumärkides!
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * >Loeb edetabeli sisu ja lisab tulemuse listi
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear();
        File file = new File(filename);
        if (!file.exists()) return scoreboard; // Kui faili ei ole tagastab listi
        try(Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                if (parts.length == 2) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    scoreboard.add(new Content(name, steps));
                }
            }
            Collections.sort(scoreboard); //Sorteerib listi

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}
