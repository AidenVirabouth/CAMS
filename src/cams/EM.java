package cams;

/**
 * EM = Enrollment Manager
 * Handles enroll, drop, swap operations.
 */
public class EM {

    private final DataStore dataStore;
    private final WM waitlistManager;

    public EM(DataStore dataStore, WM waitlistManager) {
        this.dataStore = dataStore;
        this.waitlistManager = waitlistManager;
    }

    public String enroll(String studentId, String courseCode, boolean priority) {
        Student student = dataStore.getStudent(studentId);
        Coursee course = dataStore.getCourse(courseCode);

        if (student == null) {
            return "Student with ID " + studentId + " not found.";
        }
        if (course == null) {
            return "Coursee " + courseCode + " not found.";
        }
        if (student.isEnrolledIn(courseCode)) {
            return "Student " + studentId + " is already enrolled in " + courseCode + ".";
        }

        if (!course.isFull()) {
            ER record = new ER(studentId, courseCode, ER.Status.ENROLLED);
            student.addEnrollment(record);
            course.incrementEnrollment();
            return "Enrolled " + student.getName() + " in " + courseCode + ".";
        } else {
            waitlistManager.addToWaitlist(courseCode, studentId, priority);
            ER record = new ER(studentId, courseCode, ER.Status.WAITLISTED);
            student.addEnrollment(record);
            return "Coursee " + courseCode + " is full. " +
                   "Student " + studentId + " added to waitlist" +
                   (priority ? " with priority." : ".");
        }
    }

    public String drop(String studentId, String courseCode) {
        Student student = dataStore.getStudent(studentId);
        Coursee course = dataStore.getCourse(courseCode);

        if (student == null) {
            return "Student with ID " + studentId + " not found.";
        }
        if (course == null) {
            return "Coursee " + courseCode + " not found.";
        }

        if (!student.isEnrolledIn(courseCode)) {
            return "Student " + studentId + " is not enrolled in " + courseCode + ".";
        }

        student.removeEnrollmentByCourseCode(courseCode);
        course.decrementEnrollment();

        String nextStudentId = waitlistManager.popNextFromWaitlist(courseCode);
        if (nextStudentId != null) {
            Student nextStudent = dataStore.getStudent(nextStudentId);
            if (nextStudent != null) {
                ER newRecord = new ER(nextStudentId, courseCode, ER.Status.ENROLLED);
                nextStudent.addEnrollment(newRecord);
                course.incrementEnrollment();
                return "Dropped " + studentId + " from " + courseCode +
                       ". Auto-enrolled " + nextStudentId + " from waitlist.";
            }
        }

        return "Dropped " + studentId + " from " + courseCode + ". No one on waitlist.";
    }

    public String swap(String studentId, String fromCourseCode, String toCourseCode, boolean priorityForNew) {
        Student student = dataStore.getStudent(studentId);
        if (student == null) {
            return "Student with ID " + studentId + " not found.";
        }

        if (!student.isEnrolledIn(fromCourseCode)) {
            return "Student is not enrolled in " + fromCourseCode + " so cannot swap.";
        }

        String enrollMessage = enroll(studentId, toCourseCode, priorityForNew);
        if (!enrollMessage.startsWith("Enrolled")) {
            return "Swap failed: " + enrollMessage;
        }

        String dropMessage = drop(studentId, fromCourseCode);
        return "Swap successful.\n" +
               "Enroll result: " + enrollMessage + "\n" +
               "Drop result: " + dropMessage;
    }
}
