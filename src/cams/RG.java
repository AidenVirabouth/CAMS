package cams;

import java.util.List;

/**
 * RG = Report Generator
 * Uses recursion to print a student's enrollments.
 */
public class RG {

    public void printStudentEnrollments(Student student) {
        List<ER> records = student.getCurrentEnrollments();
        System.out.println("Report for student: " + student.getName() + " (" + student.getId() + ")");
        printEnrollmentsRecursive(records, 0);
    }

    private void printEnrollmentsRecursive(List<ER> records, int index) {
        if (index >= records.size()) {
            return;
        }
        ER record = records.get(index);
        System.out.println("  - " + record.getCourseCode() +
                           " [" + record.getStatus() + "] " +
                           (record.getGrade() != null ? "grade: " + record.getGrade() : ""));
        printEnrollmentsRecursive(records, index + 1);
    }
}
