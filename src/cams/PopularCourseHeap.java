package cams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Wrapper around a heap structure to track most popular courses
 * by current enrollment. Person 1 owns this file.
 */
public class PopularCourseHeap {

    // Max-heap based on currentEnrollment
    private final PriorityQueue<Coursee> heap = new PriorityQueue<>(
            Comparator.comparingInt(Coursee::getCurrentEnrollment).reversed()
    );

    /**
     * Rebuilds the heap from a collection of courses.
     * Call this after major enrollment changes if needed.
     */
    public void buildHeap(Iterable<Coursee> courses) {
        heap.clear();
        for (Coursee c : courses) {
            heap.offer(c);
        }
    }

    /**
     * Return the top k most popular courses (by enrollment).
     * If there are fewer than k courses, return all of them.
     */
    public List<Coursee> getTopK(int k) {
        List<Coursee> result = new ArrayList<>();
        PriorityQueue<Coursee> copy = new PriorityQueue<>(heap);

        for (int i = 0; i < k && !copy.isEmpty(); i++) {
            result.add(copy.poll());
        }
        return result;
    }

    /**
     * Optionally, you can call this when a single course's enrollment changes.
     * Easiest approach: rebuild the heap using buildHeap().
     */
}
