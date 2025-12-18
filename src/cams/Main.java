package cams;

import java.util.List;

public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
        System.out.println("=== CAMS (Course Enrollment & Academic Management System) ===");
        System.out.println("Bootstrapping sample data from Person 1 backend...\n");

        // 1) Create core storage objects
        DataStore dataStore = new DataStore();
        CourseTree courseTree = new CourseTree();
        PopularCourseHeap popularHeap = new PopularCourseHeap();

        // 2) Seed some sample courses and students
        seedSampleCourses(dataStore, courseTree);
        seedSampleStudents(dataStore);

        // 3) Show what’s in the system so far
        System.out.println("All courses in DataStore:");
        for (Coursee c : dataStore.getAllCourses()) {
            System.out.println("  " + c);
        }

        System.out.println("\nAll students in DataStore:");
        for (Student s : dataStore.getAllStudents()) {
            System.out.println("  " + s);
        }

        // 4) Demonstrate the course tree (in-order = sorted by course code)
        System.out.println("\nCourses from CourseTree (in-order):");
        for (Coursee c : courseTree.inOrder()) {
            System.out.println("  " + c.getCode() + " - " + c.getTitle());
        }

        // 5) Fake some enrollments to test the popularity heap
        System.out.println("\nSimulating enrollments to test PopularCourseHeap...");
        simulateEnrollmentsForPopularityDemo(dataStore);

        // rebuild heap after "enrollments"
        popularHeap.buildHeap(dataStore.getAllCourses());
        List<Coursee> topCourses = popularHeap.getTopK(3);

        System.out.println("Top popular courses (by currentEnrollment):");
        for (Coursee c : topCourses) {
            System.out.println("  " + c.getCode() + " (" + c.getCurrentEnrollment() + " students)");
        }

        System.out.println("\n[TODO] Person 2 will replace this with a real menu/UI later.");
    }

    private static void seedSampleCourses(DataStore dataStore, CourseTree courseTree) {
        Coursee c1 = new Coursee("CSE101", "Intro to Programming", "Smith", 4, 30, "CSE");
        Coursee c2 = new Coursee("CSE210", "Data Structures", "Lee", 4, 40, "CSE");
        Coursee c3 = new Coursee("MATH120", "Calculus I", "Jones", 4, 35, "MATH");
        Coursee c4 = new Coursee("MATH220", "Linear Algebra", "Chen", 3, 40, "MATH");

        dataStore.addCourse(c1);
        dataStore.addCourse(c2);
        dataStore.addCourse(c3);
        dataStore.addCourse(c4);

        courseTree.insert(c1);
        courseTree.insert(c2);
        courseTree.insert(c3);
        courseTree.insert(c4);
    }

    private static void seedSampleStudents(DataStore dataStore) {
        Student s1 = new Student("S001", "Alice Nguyen", "Computer Science", 1);
        Student s2 = new Student("S002", "Brian Kim", "Mathematics", 2);
        Student s3 = new Student("S003", "Carla Lopez", "Computer Science", 3);

        dataStore.addStudent(s1);
        dataStore.addStudent(s2);
        dataStore.addStudent(s3);

        // Example of using the linked-list-based enrollments:
        EnrollmentRecord r1 = new EnrollmentRecord("S001", "CSE101", EnrollmentRecord.Status.ENROLLED);
        EnrollmentRecord r2 = new EnrollmentRecord("S001", "MATH120", EnrollmentRecord.Status.ENROLLED);
        s1.addEnrollment(r1);
        s1.addEnrollment(r2);
    }

    private static void simulateEnrollmentsForPopularityDemo(DataStore dataStore) {
        // Just manually bump up enrollments to test the heap
        Coursee cse101 = dataStore.getCourse("CSE101");
        Coursee cse210 = dataStore.getCourse("CSE210");
        Coursee math120 = dataStore.getCourse("MATH120");
        Coursee math220 = dataStore.getCourse("MATH220");

        if (cse101 != null) {
            for (int i = 0; i < 25; i++) cse101.incrementEnrollment();
        }
        if (cse210 != null) {
            for (int i = 0; i < 35; i++) cse210.incrementEnrollment();
        }
        if (math120 != null) {
            for (int i = 0; i < 20; i++) math120.incrementEnrollment();
        }
        if (math220 != null) {
            for (int i = 0; i < 10; i++) math220.incrementEnrollment();
        }
=======
        System.out.println("Welcome to CAMS!");
        System.out.println("Change #1 – practicing Git push.");

        // Later: call your menu and other logic here
>>>>>>> c194c52d87e7a74fed324ec577347a75cb82588e
    }
}
