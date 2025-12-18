package cams;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== CAMS (Course Enrollment & Academic Management System) ===");
        System.out.println("Bootstrapping sample data...\n");

        DataStore dataStore = new DataStore();
        CourseTree courseTree = new CourseTree();
        PCH popularHeap = new PCH();

        seedSampleCourses(dataStore, courseTree);
        seedSampleStudents(dataStore);

        System.out.println("All courses in DataStore:");
        for (Coursee c : dataStore.getAllCourses()) {
            System.out.println("  " + c);
        }

        System.out.println("\nAll students in DataStore:");
        for (Student s : dataStore.getAllStudents()) {
            System.out.println("  " + s);
        }

        System.out.println("\nCourses from CourseTree (in-order):");
        for (Coursee c : courseTree.inOrder()) {
            System.out.println("  " + c.getCode() + " - " + c.getTitle());
        }

        System.out.println("\nSimulating enrollments to test PCH...");
        simulateEnrollmentsForPopularityDemo(dataStore);

        popularHeap.buildHeap(dataStore.getAllCourses());
        List<Coursee> topCourses = popularHeap.getTopK(3);

        System.out.println("Top popular courses (by currentEnrollment):");
        for (Coursee c : topCourses) {
            System.out.println("  " + c.getCode() + " (" + c.getCurrentEnrollment() + " students)");
        }

        System.out.println("\n[TODO] Use EM/WM/HM/PC/RG/CS in a menu for the final UI.");
    }

    private static void seedSampleCourses(DataStore dataStore, CourseTree courseTree) {
        // Sports + your real class
        Coursee c1 = new Coursee("BASK101", "Basketball", "Coach Green", 3, 30, "PE");
        Coursee c2 = new Coursee("FOOT101", "Football", "Coach Brown", 3, 40, "PE");
        Coursee c3 = new Coursee("SOCC101", "Soccer", "Coach White", 3, 35, "PE");
        Coursee c4 = new Coursee("CSCI210", "CSCI 210 - Data Structures", "Mohammed Elmatary", 4, 40, "CSCI");

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
        // NBA squad
        Student s1 = new Student("S001", "LeBron James", "Kinesiology", 1);
        Student s2 = new Student("S002", "Luka Doncic", "Sports Management", 2);
        Student s3 = new Student("S003", "Austin Reaves", "Computer Science", 3);

        dataStore.addStudent(s1);
        dataStore.addStudent(s2);
        dataStore.addStudent(s3);

        // Example enrollments for LeBron
        ER r1 = new ER("S001", "BASK101", ER.Status.ENROLLED);
        ER r2 = new ER("S001", "CSCI210", ER.Status.ENROLLED);
        s1.addEnrollment(r1);
        s1.addEnrollment(r2);
    }

    private static void simulateEnrollmentsForPopularityDemo(DataStore dataStore) {
        Coursee bask = dataStore.getCourse("BASK101");
        Coursee foot = dataStore.getCourse("FOOT101");
        Coursee socc = dataStore.getCourse("SOCC101");
        Coursee csci210 = dataStore.getCourse("CSCI210");

        if (bask != null) {
            for (int i = 0; i < 35; i++) bask.incrementEnrollment(); // super popular
        }
        if (foot != null) {
            for (int i = 0; i < 25; i++) foot.incrementEnrollment();
        }
        if (socc != null) {
            for (int i = 0; i < 20; i++) socc.incrementEnrollment();
        }
        if (csci210 != null) {
            for (int i = 0; i < 15; i++) csci210.incrementEnrollment();
        }
    }
}
