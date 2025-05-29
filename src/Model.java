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
    private boolean isBackdoorUsed = false;  // Tagauks

    /**
     * Tagaukse kasutamine
     * @return tagauks
     */
    public boolean isBackdoorUsed() {
        return isBackdoorUsed;
    }

    /**'
     * Uuue mängu loomine
     */
    public void initGame() {
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;
        isBackdoorUsed = false;

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
        if (guess == 1000) {
            game_over = true;
            isBackdoorUsed = true;
            return "Tagauks on lahti! Õige number on: " + pc_number;
        }
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
    public void saveScore(String name, long durationMillis) {
        loadScores(); //laeb failst sisu listi
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        scoreboard.add(new Content(timestamp, name, steps, durationMillis)); // Lisa nimi ja sammude arv LISTI
        Collections.sort(scoreboard); //Sorteerib listi
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Content c : scoreboard) {
                out.println(c.getTimestamp() + ";" + c.getName() + ";" + c.getSteps() + ";" + c.getDurationMillis()); //Semikoolon juttumärkides!
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
                if (parts.length == 4) {
                    String timestamp = parts[0];
                    String name = parts[1];
                    int steps = Integer.parseInt(parts[2]);
                    long durationMillis = Long.parseLong(parts[3]);
                    scoreboard.add(new Content(timestamp, name, steps, durationMillis));
                }
            }
            Collections.sort(scoreboard); //Sorteerib listi

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}