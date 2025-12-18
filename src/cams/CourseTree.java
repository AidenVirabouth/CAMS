package cams;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree of courses keyed by course code.
 * Person 1 owns this file. You can later upgrade this to AVL for self-balancing.
 */
public class CourseTree {

    private static class Node {
        Coursee course;
        Node left;
        Node right;

        Node(Coursee course) {
            this.course = course;
        }
    }

    private Node root;

    public void insert(Coursee course) {
        root = insertRecursive(root, course);
    }

    private Node insertRecursive(Node node, Coursee course) {
        if (node == null) {
            return new Node(course);
        }
        int cmp = course.getCode().compareTo(node.course.getCode());
        if (cmp < 0) {
            node.left = insertRecursive(node.left, course);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, course);
        } else {
            // same code: replace or ignore (here we replace)
            node.course = course;
        }
        return node;
    }

    public Coursee searchByCode(String code) {
        Node curr = root;
        while (curr != null) {
            int cmp = code.compareTo(curr.course.getCode());
            if (cmp == 0) {
                return curr.course;
            } else if (cmp < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    /**
     * In-order traversal (sorted by course code).
     */
    public List<Coursee> inOrder() {
        List<Coursee> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node node, List<Coursee> result) {
        if (node == null) return;
        inOrderRecursive(node.left, result);
        result.add(node.course);
        inOrderRecursive(node.right, result);
    }

    // TODO: For self-balancing requirement, you can later convert this
    // to an AVL tree by adding height fields and rotations.
}
