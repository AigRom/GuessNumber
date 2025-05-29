/**
 * Edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String timestamp;
    private final String name;
    private final int steps;
    private final long durationMillis;



    /**
     * Objekti loomise konstruktor
     * @param timestamp Mängu lõpetamise kuupäev ja kellaaeg
     * @param name Mängija nimi
     * @param steps Sammude arv
     */
    public Content(String timestamp, String name, int steps, long durationMillis) {
        this.timestamp = timestamp;
        this.name = name;
        this.steps = steps;
        this.durationMillis = durationMillis;
    }

    /**
     * tagastab mängimise kuupäeva ja kellaaja
     * @return Mängimise kuupäev ja kellaaeg
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Tagastab mängija nime  failist
     * @return mängija nimi
     */
    public String getName() {
        return name;
    }

    /**
     *Tagastab sammude arvu
     * @return sammude arv
     */
    public int getSteps() {
        return steps;
    }

    /**
     * tTagastab mänguaja millisekundites
     * @return mängu aeg
     */
    public long getDurationMillis() {
        return durationMillis;
    }



    /**
     * Sorteerimine sammude järgi kahanevalt, kui Samad sammud ja Nimi siis kiirem ees.
     * @param o the objekt mida võrrelda
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        int stepComparison = Integer.compare(this.steps, o.steps);
        if (stepComparison != 0) {
            return stepComparison;
        }

        // Kui sammude arv ja nimi on samad → kiiremini lõpetanu on ees
        if (this.name.equals(o.name)) {
            return Long.compare(this.durationMillis, o.durationMillis);
        }

        // Muul juhul jäta järjestus muutmata
        return 0;
    }


    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        String date = timestamp.replaceFirst("(\\d{4})-(\\d{2})-(\\d{2})", "$3.$2.$1");
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name);
        String t = String.format("%-25s", date);
        String n = String.format("%-15s", displayName);
        String s = String.format("%-3d", steps);
        String d = String.format("%-3d", durationMillis);
        return t + n + s + d;
    }


}