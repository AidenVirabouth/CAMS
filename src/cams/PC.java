package cams;

import java.util.*;

/**
 * PC = Prerequisite Checker
 * Uses recursion to verify prerequisite chains.
 */
public class PC {

    private final Map<String, List<String>> prereqMap = new HashMap<>();

    public void setPrerequisites(String courseCode, List<String> prereqs) {
        prereqMap.put(courseCode, new ArrayList<>(prereqs));
    }

    public boolean hasCompletedAllPrereqs(Student student, String targetCourseCode) {
        return hasCompletedAllPrereqs(student, targetCourseCode, new HashSet<>());
    }

    private boolean hasCompletedAllPrereqs(Student student,
                                           String courseCode,
                                           Set<String> visited) {
        if (visited.contains(courseCode)) {
            return true; // avoid cycles
        }
        visited.add(courseCode);

        List<String> prereqs = prereqMap.get(courseCode);
        if (prereqs == null || prereqs.isEmpty()) {
            return true;
        }

        for (String prereqCode : prereqs) {
            if (!hasTakenCourse(student, prereqCode)) {
                return false;
            }
            if (!hasCompletedAllPrereqs(student, prereqCode, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasTakenCourse(Student student, String courseCode) {
        for (ER record : student.getCurrentEnrollments()) {
            if (courseCode.equals(record.getCourseCode())) {
                ER.Status status = record.getStatus();
                if (status == ER.Status.ENROLLED || status == ER.Status.COMPLETED) {
                    return true;
                }
            }
        }
        return false;
    }
}
