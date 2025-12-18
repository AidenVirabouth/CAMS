package cams;

/**
 * ER = Enrollment Record
 * Represents a single enrollment of a student in a course.
 */
public class ER {

    public enum Status {
        ENROLLED,
        COMPLETED,
        DROPPED,
        WAITLISTED
    }

    private final String studentId;
    private final String courseCode;
    private Status status;
    private Double grade; // optional, can be null

    public ER(String studentId, String courseCode, Status status) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.status = status;
        this.grade = null;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ER{" +
               "studentId='" + studentId + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", status=" + status +
               ", grade=" + grade +
               '}';
    }
}
