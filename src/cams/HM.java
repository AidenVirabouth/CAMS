package cams;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * HM = History Manager
 * Tracks actions for undo/redo using stacks.
 */
public class HM {

    public enum ActionType {
        ENROLL,
        DROP,
        SWAP
    }

    public static class Action {
        public final ActionType type;
        public final String studentId;
        public final String courseCode;
        public final String extraCourseCode; // for swap

        public Action(ActionType type, String studentId, String courseCode) {
            this(type, studentId, courseCode, null);
        }

        public Action(ActionType type, String studentId, String courseCode, String extraCourseCode) {
            this.type = type;
            this.studentId = studentId;
            this.courseCode = courseCode;
            this.extraCourseCode = extraCourseCode;
        }

        @Override
        public String toString() {
            return "Action{" +
                   "type=" + type +
                   ", studentId='" + studentId + '\'' +
                   ", courseCode='" + courseCode + '\'' +
                   ", extraCourseCode='" + extraCourseCode + '\'' +
                   '}';
        }
    }

    private final Deque<Action> undoStack = new ArrayDeque<>();
    private final Deque<Action> redoStack = new ArrayDeque<>();

    public void record(Action action) {
        undoStack.push(action);
        redoStack.clear();
    }

    public Action popUndo() {
        if (undoStack.isEmpty()) return null;
        Action action = undoStack.pop();
        redoStack.push(action);
        return action;
    }

    public Action popRedo() {
        if (redoStack.isEmpty()) return null;
        Action action = redoStack.pop();
        undoStack.push(action);
        return action;
    }

    public boolean hasUndo() {
        return !undoStack.isEmpty();
    }

    public boolean hasRedo() {
        return !redoStack.isEmpty();
    }
}
