package cams;

import java.util.*;

/**
 * Central storage for all students and courses.
 */
public class DataStore {

    private final Map<String, Student> studentsById = new HashMap<>();
    private final Map<String, Coursee> coursesByCode = new HashMap<>();

    private final Set<String> departments = new HashSet<>();
    private final Set<String> majors = new HashSet<>();

    // ----- Student methods -----

    public void addStudent(Student student) {
        studentsById.put(student.getId(), student);
        majors.add(student.getMajor());
    }

    public Student getStudent(String studentId) {
        return studentsById.get(studentId);
    }

    public boolean hasStudent(String studentId) {
        return studentsById.containsKey(studentId);
    }

    public Collection<Student> getAllStudents() {
        return studentsById.values();
    }

    // ----- Coursee methods -----

    public void addCourse(Coursee course) {
        coursesByCode.put(course.getCode(), course);
        if (course.getDepartment() != null) {
            departments.add(course.getDepartment());
        }
    }

    public Coursee getCourse(String courseCode) {
        return coursesByCode.get(courseCode);
    }

    public boolean hasCourse(String courseCode) {
        return coursesByCode.containsKey(courseCode);
    }

    public void removeCourse(String courseCode) {
        coursesByCode.remove(courseCode);
    }

    public Collection<Coursee> getAllCourses() {
        return coursesByCode.values();
    }

    public List<Coursee> getCoursesByDepartment(String department) {
        List<Coursee> result = new ArrayList<>();
        for (Coursee c : coursesByCode.values()) {
            if (department.equalsIgnoreCase(c.getDepartment())) {
                result.add(c);
            }
        }
        return result;
    }

    // ----- Department / major sets -----

    public Set<String> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }

    public Set<String> getMajors() {
        return Collections.unmodifiableSet(majors);
    }
}
