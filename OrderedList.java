/**
 * Author: Kevin Abrahams
 * Date: June 10, 2020
 * Class Description: This class implements an ordered list
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderedList {
    /**
     * Check list is in sorted order or not
     *
     * @param list - List
     * @return True/False
     */
    public static boolean checkSorted(List<Polynomial> list) {
        return checkSorted(list, null);
    }

    /**
     * Check list is in sorted order or not
     *
     * @param list       - List
     * @param comparator - Comparator
     * @return True/False
     */
    public static boolean checkSorted(List<Polynomial> list, Comparator<Polynomial> comparator) {
        boolean isSorted = false;
        List<Polynomial> tmp = new ArrayList<Polynomial>(list);
        if (null != comparator) {
            Collections.sort(tmp, comparator);
        } else {
            Collections.sort(tmp);
        }
        isSorted = tmp.equals(list);
        return isSorted;
    }
}