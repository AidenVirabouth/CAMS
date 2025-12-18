package cams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * PCH = Popular Course Heap
 * Tracks most popular courses by currentEnrollment.
 */
public class PCH {

    private final PriorityQueue<Coursee> heap = new PriorityQueue<>(
            Comparator.comparingInt(Coursee::getCurrentEnrollment).reversed()
    );

    public void buildHeap(Iterable<Coursee> courses) {
        heap.clear();
        for (Coursee c : courses) {
            heap.offer(c);
        }
    }

    public List<Coursee> getTopK(int k) {
        List<Coursee> result = new ArrayList<>();
        PriorityQueue<Coursee> copy = new PriorityQueue<>(heap);

        for (int i = 0; i < k && !copy.isEmpty(); i++) {
            result.add(copy.poll());
        }
        return result;
    }
}
