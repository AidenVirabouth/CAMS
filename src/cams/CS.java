package cams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * CS = Course Sorter
 * Sorts lists of Coursee using a custom insertion sort.
 */
public class CS {

    public List<Coursee> sortByTitle(List<Coursee> courses) {
        return insertionSort(new ArrayList<>(courses),
                Comparator.comparing(Coursee::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    public List<Coursee> sortByDepartmentThenTitle(List<Coursee> courses) {
        return insertionSort(new ArrayList<>(courses),
                Comparator.comparing(Coursee::getDepartment, String.CASE_INSENSITIVE_ORDER)
                          .thenComparing(Coursee::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    public List<Coursee> sortByEnrollmentDescending(List<Coursee> courses) {
        return insertionSort(new ArrayList<>(courses),
                Comparator.comparingInt(Coursee::getCurrentEnrollment).reversed());
    }

    private List<Coursee> insertionSort(List<Coursee> list,
                                        Comparator<Coursee> comparator) {
        for (int i = 1; i < list.size(); i++) {
            Coursee key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return list;
    }
}
