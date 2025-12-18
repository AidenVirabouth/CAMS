package cams;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student in the CAMS system.
 * Uses a singly linked list of ER (enrollment records).
 */
public class Student {
    private String id;
    private String name;
    private String major;
    private int year;

    // Singly-linked list of current enrollments
    private EnrollmentNode enrollmentHead;

    private static class EnrollmentNode {
        ER record;
        EnrollmentNode next;

        EnrollmentNode(ER record) {
            this.record = record;
        }
    }

    public Student(String id, String name, String major, int year) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.year = year;
    }

    // ----- Basic getters/setters -----

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // ----- Enrollment (singly linked list) -----

    /**
     * Add a new enrollment to the front of the linked list.
     */
    public void addEnrollment(ER record) {
        EnrollmentNode node = new EnrollmentNode(record);
        node.next = enrollmentHead;
        enrollmentHead = node;
    }

    /**
     * Remove an enrollment for a given course code (if it exists).
     */
    public void removeEnrollmentByCourseCode(String courseCode) {
        EnrollmentNode prev = null;
        EnrollmentNode curr = enrollmentHead;

        while (curr != null) {
            if (Objects.equals(curr.record.getCourseCode(), courseCode)) {
                if (prev == null) {
                    enrollmentHead = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    /**
     * Return a list view of current enrollments for easier use.
     */
    public List<ER> getCurrentEnrollments() {
        List<ER> result = new ArrayList<>();
        EnrollmentNode curr = enrollmentHead;
        while (curr != null) {
            result.add(curr.record);
            curr = curr.next;
        }
        return result;
    }

    /**
     * Check if the student is currently enrolled in a given course.
     */
    public boolean isEnrolledIn(String courseCode) {
        EnrollmentNode curr = enrollmentHead;
        while (curr != null) {
            if (Objects.equals(curr.record.getCourseCode(), courseCode)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", major='" + major + '\'' +
               ", year=" + year +
               '}';
    }
}
