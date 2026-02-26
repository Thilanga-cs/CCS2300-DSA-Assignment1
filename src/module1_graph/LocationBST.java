package module1_graph;

public class LocationBST {

    static class Node {
        String name;
        Node left, right;
        Node(String name) { this.name = name; }
    }

    private Node root;

    public boolean insert(String name) {
        name = clean(name);
        if (name.isEmpty()) return false;

        if (root == null) { root = new Node(name); return true; }

        Node cur = root;
        while (true) {
            int cmp = name.compareToIgnoreCase(cur.name);
            if (cmp == 0) return false; // duplicate
            if (cmp < 0) {
                if (cur.left == null) { cur.left = new Node(name); return true; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = new Node(name); return true; }
                cur = cur.right;
            }
        }
    }

    public boolean contains(String name) {
        name = clean(name);
        Node cur = root;
        while (cur != null) {
            int cmp = name.compareToIgnoreCase(cur.name);
            if (cmp == 0) return true;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    public boolean remove(String name) {
        name = clean(name);
        if (!contains(name)) return false;
        root = removeRec(root, name);
        return true;
    }

    private Node removeRec(Node node, String name) {
        if (node == null) return null;

        int cmp = name.compareToIgnoreCase(node.name);
        if (cmp < 0) node.left = removeRec(node.left, name);
        else if (cmp > 0) node.right = removeRec(node.right, name);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node min = node.right;
            while (min.left != null) min = min.left;
            node.name = min.name;
            node.right = removeRec(node.right, min.name);
        }
        return node;
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        System.out.println("Locations in BST (sorted):");
        inOrder(root);
    }

    private void inOrder(Node n) {
        if (n == null) return;
        inOrder(n.left);
        System.out.println("- " + n.name);
        inOrder(n.right);
    }

    private static String clean(String s) {
        return (s == null) ? "" : s.trim();
    }
}
