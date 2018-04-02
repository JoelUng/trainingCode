package joel.ung.trainingcode;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import java.io.File;

public class Main {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String FILENAME_MAC = "/Users/joelung/projets/trainingCode/src/main/resources/input2.txt";
    private static final String FILENAME_WINDOWS = "d:\\projets\\perso\\trainingCode\\src\\main\\resources\\input4.txt";

    public static void main(String[] args) throws Exception {
        String fileName = isWindows() ? FILENAME_WINDOWS : FILENAME_MAC;
        File file = new File(fileName);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            final int nbRestaurant = lireNombreRestaurant(sc.nextLine());
            Double meilleurScore = new Double(0);
            long start = System.currentTimeMillis();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Double scoreLigne = calculMeilleurScoreStream(line);
                // Double scoreLigne = calculMeilleurScoreLoop(line);
                meilleurScore = scoreLigne > meilleurScore ? scoreLigne : meilleurScore;
            }
            System.out.println(meilleurScore.intValue());
            long end = System.currentTimeMillis();
            System.out.print("temps de calcul : ");
            System.out.print(end - start);
            System.out.println(" ms");
            System.exit(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    /**
     * Calcule la note moyenne d'une chaine de notes arrondi à l'entier supérieur en utilisant java.util.Stream.
     * @param line la chaine de notes
     * @return la note moyenne d'une chaine de notes arrondi à l'entier supérieur
     */
    private static Double calculMeilleurScoreStream(final String line) {
        OptionalDouble noteRestaurant = Pattern.compile(" ")
                .splitAsStream(line)
                .mapToInt(Integer::parseInt)
                .average();
        return noteRestaurant.isPresent() ? Math.ceil(noteRestaurant.getAsDouble()) : new Double(0);
    }

    /**
     * Calcule la note moyenne d'une chaine de notes arrondi à l'entier supérieur en utilisant java.util.Stream.
     * @param line la chaine de notes
     * @return la note moyenne d'une chaine de notes arrondi à l'entier supérieur
     */
    private static Double calculMeilleurScoreLoop(final String line) {
        String[] notes = line.split("\\s+");
        Integer sommeNote = 0;
        for (String note : notes) {
            Integer noteEntier = Integer.parseInt(note);
            sommeNote += noteEntier;
        }
        Double moyenne = sommeNote.doubleValue() / notes.length;
        return Math.ceil(moyenne);
    }

    private static Integer lireNombreRestaurant(final String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }
}
