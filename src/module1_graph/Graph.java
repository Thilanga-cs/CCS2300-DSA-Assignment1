package module1_graph;

import java.util.*;

public class Graph {
    // location -> neighbors
    private final Map<String, Set<String>> adj = new HashMap<>();

    public boolean addLocation(String name) {
        name = clean(name);
        if (name.isEmpty()) return false;
        String key = name.toLowerCase();
        if (adj.containsKey(key)) return false;

        adj.put(key, new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
        return true;
    }

    public boolean hasLocation(String name) {
        return adj.containsKey(clean(name).toLowerCase());
    }

    public boolean removeLocation(String name) {
        name = clean(name);
        String key = name.toLowerCase();
        if (!adj.containsKey(key)) return false;

        // remove this name from other neighbor sets
        for (Set<String> neighbors : adj.values()) {
            neighbors.remove(name);
        }
        adj.remove(key);
        return true;
    }

    public boolean addRoad(String a, String b) {
        a = clean(a); b = clean(b);
        if (a.isEmpty() || b.isEmpty()) return false;
        if (a.equalsIgnoreCase(b)) return false;
        if (!hasLocation(a) || !hasLocation(b)) return false;

        adj.get(a.toLowerCase()).add(b);
        adj.get(b.toLowerCase()).add(a);
        return true;
    }

    public boolean removeRoad(String a, String b) {
        a = clean(a); b = clean(b);
        if (!hasLocation(a) || !hasLocation(b)) return false;

        boolean r1 = adj.get(a.toLowerCase()).remove(b);
        boolean r2 = adj.get(b.toLowerCase()).remove(a);
        return r1 || r2;
    }

    public Set<String> getNeighbors(String name) {
        name = clean(name);
        if (!hasLocation(name)) return Collections.emptySet();
        return adj.get(name.toLowerCase());
    }

    public List<String> getAllLocationsSorted() {
        List<String> keys = new ArrayList<>(adj.keySet());
        keys.sort(String.CASE_INSENSITIVE_ORDER);
        return keys;
    }

    public void displayConnections() {
        if (adj.isEmpty()) {
            System.out.println("No locations in graph.");
            return;
        }
        System.out.println("\n--- Connections ---");
        for (String key : getAllLocationsSorted()) {
            Set<String> neighbors = adj.get(key);
            System.out.println(key + " -> " + (neighbors.isEmpty() ? "[no roads]" : neighbors));
        }
    }

    private static String clean(String s) {
        return (s == null) ? "" : s.trim();
    }
}
