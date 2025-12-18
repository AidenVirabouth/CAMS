package cams;

import java.util.*;

/**
 * WM = Waitlist Manager
 * Manages waitlists for courses using queues/deques.
 */
public class WM {

    // courseCode -> deque of studentIds
    private final Map<String, Deque<String>> waitlists = new HashMap<>();

    public void addToWaitlist(String courseCode, String studentId, boolean priority) {
        Deque<String> queue = waitlists.computeIfAbsent(courseCode, k -> new ArrayDeque<>());
        if (priority) {
            queue.addFirst(studentId);
        } else {
            queue.addLast(studentId);
        }
    }

    public String popNextFromWaitlist(String courseCode) {
        Deque<String> queue = waitlists.get(courseCode);
        if (queue == null) {
            return null;
        }
        String next = queue.pollFirst();
        if (queue.isEmpty()) {
            waitlists.remove(courseCode);
        }
        return next;
    }

    public List<String> getWaitlist(String courseCode) {
        Deque<String> queue = waitlists.get(courseCode);
        if (queue == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(queue);
    }

    public boolean hasWaitlist(String courseCode) {
        Deque<String> queue = waitlists.get(courseCode);
        return queue != null && !queue.isEmpty();
    }
}
