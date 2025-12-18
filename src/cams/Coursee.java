package cams;

import java.util.Objects;

/**
 * Coursee = course in CAMS.
 */
public class Coursee {
    private String code;
    private String title;
    private String instructor;
    private int credits;
    private int capacity;

    private int currentEnrollment;
    private String department;

    public Coursee(String code,
                   String title,
                   String instructor,
                   int credits,
                   int capacity,
                   String department) {
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.credits = credits;
        this.capacity = capacity;
        this.department = department;
        this.currentEnrollment = 0;
    }

    // ----- Getters/setters -----

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public String getDepartment() {
        return department;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // ----- Enrollment helpers -----

    public boolean isFull() {
        return currentEnrollment >= capacity;
    }

    public void incrementEnrollment() {
        if (!isFull()) {
            currentEnrollment++;
        }
    }

    public void decrementEnrollment() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }

    @Override
    public String toString() {
        return "Coursee{" +
               "code='" + code + '\'' +
               ", title='" + title + '\'' +
               ", instructor='" + instructor + '\'' +
               ", credits=" + credits +
               ", capacity=" + capacity +
               ", currentEnrollment=" + currentEnrollment +
               ", department='" + department + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coursee)) return false;
        Coursee course = (Coursee) o;
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
