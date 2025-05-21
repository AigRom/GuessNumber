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
        System.out.print("Sisesta number: ");
        return Integer.parseInt(scanner.nextLine());

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
