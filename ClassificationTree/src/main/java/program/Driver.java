package program;

import java.io.IOException;
import java.util.Scanner;

/**
 * Driver for the classification program
 *
 * @author Marcus Goeckner
 */
public class Driver {

    /**
     * Runs the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Enter a file to  load > ");
        Scanner in = new Scanner(System.in);
        String fileName = in.next();
        ClassificationTree tree = new ClassificationTree(fileName);
        String more = "Y";
        while (more.equals("Y")) {
            System.out.println("Do you have another animal to identify? (Y/N) > ");
            more = in.next().toUpperCase();

            if (more.equals("Y")) {
                tree.identify();
            }
        }
        System.out.println("Enter a file name to save the tree to > ");
        fileName = in.next();
        tree.save(fileName);
    }
}
