/**
 * Author: Kevin Abrahams
 * Date: June 10, 2020
 * Class Description: The class serves as an implementation for the polynomials
 * of the program. This class shall be used to define each individual
 * polynomial to be created from an input file.
 *
 */
import java.util.Iterator;

public class Polynomial implements Iterable<Polynomial.Node>, Comparable<Polynomial> {

    private Node head;// Head of the linked list

    /**
     * Constructor to initialize the polynomials
     *
     * @param polynomial - polynomial string
     */
    public Polynomial(String polynomial) {
        try {
            String parts[] = polynomial.split(" ");
            int n = parts.length / 2;
            int exponents[] = new int[n];
            int i = 0;
            for (int j = 0; j < parts.length; j++) {

                Node node = new Node();
                node.coefficient = Double.valueOf(parts[j]);
                j++;
                node.exponent = Integer.valueOf(parts[j]);
                if (null == head) {
                    head = node;
                    exponents[i] = node.exponent;
                } else {
                    Node current = head;
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = node;
                    exponents[i] = node.exponent;
                }
                i++;

            }
            boolean isSorted = false;
            if (n == 0 || n == 1)
                isSorted = true;
            else {
                for (int j = 1; j < n; j++)
                    if (exponents[j - 1] > exponents[j])
                        isSorted = true;
                    else {
                        isSorted = false;
                        break;

                    }
            }
            if (!isSorted)
                throw new InvalidPolynomialSyntax("Invalid Polynomial.Exponents are not in sorted order");
        } catch (Exception e) {
            throw new InvalidPolynomialSyntax("Invalid Polynomial." + e.getMessage());
        }

    }

    @Override
    public int compareTo(Polynomial o) {
        Iterator<Node> it1 = iterator();
        Iterator<Node> it2 = o.iterator();
        int cm = -1;
        while (it1.hasNext()) {
            Node n1 = it1.next();
            if (it2.hasNext()) {
                Node n2 = it2.next();
                if (n1.exponent != 0 && n2.exponent != 0)
                    cm = Double.compare(n1.exponent, n2.exponent);
                if (cm == 0) {
                    cm = Double.compare(n1.coefficient, n2.coefficient);
                    if (cm != 0) {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                cm = 1;
                break;
            }
        }

        return cm;
    }

    @Override
    public Iterator<Node> iterator() {
        return new PolynomialIterator(head);
    }

    @Override
    public String toString() {
        String str = "";
        Node node = head;
        while (null != node) {
            if (node.exponent != 0) {
                if (node.exponent == 1)
                    str += node.coefficient + "x + ";
                else
                    str += node.coefficient + "x^" + node.exponent + " + ";
            } else {
                str += node.coefficient;
            }
            node = node.next;
        }
        return str;
    }

    /**
     * Author: Kevin Abrahams
     * Class Description: This class implements a node.
     */
    public static class Node {
        // Coefficient of polynomial
        double coefficient;
        // Exponent of polynomial
        int exponent;
        Node next;// Next node

    }

    /**
     * Author: Kevin Abrahams
     * Class Description: This class implements the polynomial iterator.
     */
    public class PolynomialIterator implements Iterator<Node> {
        private Node current;// Current node

        public PolynomialIterator(Node head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node next() {
            if (hasNext()) {
                Node node = current;
                current = current.next;
                return node;
            }
            return null;
        }

    }

}