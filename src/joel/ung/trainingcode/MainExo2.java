package joel.ung.trainingcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainExo2 {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String FILENAME_MAC = "/Users/joelung/projets/trainingCode/src/main/resources/exo2/input1.txt";
    private static final String FILENAME_WINDOWS = "d:\\projets\\perso\\trainingCode\\src\\main\\resources\\exo2\\input3.txt";
    private static final double remise0 = 0.0;
    private static final double remise10 = 0.1;
    private static final double remise20 = 0.2;
    private static final double remise30 = 0.3;


    public static void main(String[] args) throws Exception {
        String fileName = isWindows() ? FILENAME_WINDOWS : FILENAME_MAC;
        File file = new File(fileName);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            Integer prixBuffetParPersonne = lireNombreDansLigne(sc.nextLine());
            Integer nbTablesServies = lireNombreDansLigne(sc.nextLine());
            Integer nbPersonnes = 0;
            Double montantVentes = 0.0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                nbPersonnes = lireNombreDansLigne(line);
                montantVentes = montantVentes + calculNoteTable(nbPersonnes, prixBuffetParPersonne);
            }
            montantVentes = Math.ceil(montantVentes);
            System.out.println(montantVentes.intValue());
            System.exit(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static Double calculNoteTable(final Integer nbPersonnes, final Integer prixBuffetParPersonne) {
        Double remiseCalcul = remise0;
        if (4 <= nbPersonnes && nbPersonnes < 6) {
            remiseCalcul = remise10;
        }
        else if (6 <= nbPersonnes && nbPersonnes < 10) {
            remiseCalcul = remise20;
        }
        else if (10 <= nbPersonnes) {
            remiseCalcul = remise30;
        }
        return (nbPersonnes * prixBuffetParPersonne) - (nbPersonnes * prixBuffetParPersonne * remiseCalcul);
    }

    private static Integer lireNombreDansLigne(String line) {
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
