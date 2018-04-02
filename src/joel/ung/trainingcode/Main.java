package joel.ung.trainingcode;

import java.util.*;
import java.io.File;

public class Main {

    private static final String FILENAME = "/Users/joelung/projets/trainingCode/src/main/resources/input1.txt";

    public static void main(String[] args) throws Exception {
        String  line;
        File file = new File(FILENAME);
        Scanner sc = new Scanner(file);
        System.out.println(sc.hasNextLine());
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.out.println(line);
        }
        System.exit(0);

    }
}
