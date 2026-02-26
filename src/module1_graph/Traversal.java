package module1_graph;

import java.util.*;

public class Traversal {

    public static void bfs(Graph g, String start) {
        start = clean(start);
        if (!g.hasLocation(start)) {
            System.out.println("Start location not found.");
            return;
        }

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        q.add(start);
        visited.add(start.toLowerCase());

        System.out.print("BFS Order: ");
        while (!q.isEmpty()) {
            String cur = q.poll();
            System.out.print(cur + " ");

            for (String nb : g.getNeighbors(cur)) {
                String k = nb.toLowerCase();
                if (!visited.contains(k)) {
                    visited.add(k);
                    q.add(nb);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(Graph g, String start) {
        start = clean(start);
        if (!g.hasLocation(start)) {
            System.out.println("Start location not found.");
            return;
        }

        Stack<String> st = new Stack<>();
        Set<String> visited = new HashSet<>();

        st.push(start);

        System.out.print("DFS Order: ");
        while (!st.isEmpty()) {
            String cur = st.pop();
            String k = cur.toLowerCase();
            if (visited.contains(k)) continue;

            visited.add(k);
            System.out.print(cur + " ");

            // reverse neighbors for nicer output order
            List<String> nbs = new ArrayList<>(g.getNeighbors(cur));
            Collections.reverse(nbs);
            for (String nb : nbs) {
                if (!visited.contains(nb.toLowerCase())) st.push(nb);
            }
        }
        System.out.println();
    }

    private static String clean(String s) {
        return (s == null) ? "" : s.trim();
    }
}
