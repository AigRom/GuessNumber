import java.util.List;
import java.util.Scanner;

/**
 * Kõik mida kasutaja näeb on siin
 */
public class View {
    private final Scanner scanner = new Scanner(System.in);


    /**
     * Mängu menüü näitamine
     */
    public void showMenu() {
        System.out.println("1. Mängima");
        System.out.println("2. Edetabel");
        System.out.println("3. Välju");
        System.out.print("Tee valik: ");
    }

    /**
     * Tagastab kasutaja sisestuse
     * @return kasutaja sisestus
     */
    public int getMenuChoice() {
        return Integer.parseInt(scanner.nextLine());

    }

    /**
     * Väljastab  etteantud teate konsiooli
     * @param message teade mida väljastada
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Küsib kasutajalt numbrit
     * @return kasuraja sisestatud number
     */
    public int askGuess() {
        while (true) {
            System.out.print("Sisesta number: ");
            String input = (scanner.nextLine().trim());

            try {
                int guess = Integer.parseInt(input);
                if (guess >= 1 && guess <= 100 || guess == 1000) {
                    return guess;
                } else {
                    System.out.println("Palun sisesta arv vahemikus 1-100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("See ei ole arv. Palun proovi uuesti");
            }
        }


    }

    /**
     * Küsib kasutajalt nime
     * @return sisestatud nimi
     */
    public String askName() {
        System.out.print("Sisesta nimi: ");
        return scanner.nextLine();
    }

    public void showScoreboard(List<Content> scores) {
        System.out.println("EDETABEL");
        System.out.println( "________");
        for (Content sc: scores) {
            System.out.println(sc.formattedData());
        }
        System.out.println();









    }
}