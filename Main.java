/**
 * Author: Kevin Abrahams
 * Date: June 10, 2020
 * Class Description: This class is the Main class of the application
 * which provides the GUI for selecting an input file to create
 * polynomials and display polynomial data.
 */
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * Main method of the application
     *
     * @param args - Array of the command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<Polynomial> polynomials = new ArrayList<Polynomial>();
        JFileChooser chooser = new JFileChooser();
        int r = chooser.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            String filePath = chooser.getSelectedFile().getAbsolutePath();
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNext()) {
                try {
                    Polynomial p = new Polynomial(sc.nextLine());
                    System.out.println(p.toString());
                    polynomials.add(p);
                } catch (InvalidPolynomialSyntax e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
            sc.close();
            System.out.println("Polynomials are in strict ascending order:" + OrderedList.checkSorted(polynomials));

            boolean isWeakSorted = OrderedList.checkSorted(polynomials, (Polynomial p1, Polynomial p2) -> {

                Iterator<Polynomial.Node> it1 = p1.iterator();
                Iterator<Polynomial.Node> it2 = p2.iterator();
                int cm = -1;
                while (it1.hasNext()) {
                    Polynomial.Node n1 = it1.next();
                    if (it2.hasNext()) {
                        Polynomial.Node n2 = it2.next();
                        if (n1.exponent != 0 && n2.exponent != 0)
                            cm = Double.compare(n1.exponent, n2.exponent);
                        if (cm != 0) {
                            break;
                        }
                    } else {
                        cm = 1;
                        break;
                    }
                }

                return cm;

            });
            System.out.println("Polynomials are in weak ascending order:" + isWeakSorted);
        }
    }

}