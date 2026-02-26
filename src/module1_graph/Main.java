package module1_graph;

import java.util.Scanner;

public class Main {

    static int readInt(Scanner sc, String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) System.out.println("Enter " + min + " to " + max + ".");
                else return v;
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    static String readText(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Cannot be empty.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LocationBST bst = new LocationBST();
        Graph graph = new Graph();

        while (true) {
            System.out.println("\n=== Module 1: Smart City Route Planner ===");
            System.out.println("1) Add Location (BST -> Graph)");
            System.out.println("2) Remove Location (BST + Graph)");
            System.out.println("3) Add Road");
            System.out.println("4) Remove Road");
            System.out.println("5) Display Connections");
            System.out.println("6) BFS Traverse");
            System.out.println("7) DFS Traverse");
            System.out.println("8) Show Locations (BST)");
            System.out.println("0) Exit");

            int c = readInt(sc, "Choose: ", 0, 8);

            if (c == 0) break;

            switch (c) {
                case 1 -> {
                    String name = readText(sc, "Location name: ");
                    boolean okBST = bst.insert(name);
                    if (!okBST) {
                        System.out.println("Location already exists.");
                        break;
                    }
                    graph.addLocation(name);
                    System.out.println("Added ✅ " + name);
                }
                case 2 -> {
                    String name = readText(sc, "Remove location: ");
                    boolean r1 = bst.remove(name);
                    boolean r2 = graph.removeLocation(name);
                    System.out.println((r1 || r2) ? "Removed ✅" : "Not found.");
                }
                case 3 -> {
                    String a = readText(sc, "From: ");
                    String b = readText(sc, "To: ");
                    System.out.println(graph.addRoad(a, b) ? "Road added ✅" : "Failed (check names).");
                }
                case 4 -> {
                    String a = readText(sc, "From: ");
                    String b = readText(sc, "To: ");
                    System.out.println(graph.removeRoad(a, b) ? "Road removed ✅" : "No such road.");
                }
                case 5 -> graph.displayConnections();
                case 6 -> {
                    String start = readText(sc, "BFS start: ");
                    Traversal.bfs(graph, start);
                }
                case 7 -> {
                    String start = readText(sc, "DFS start: ");
                    Traversal.dfs(graph, start);
                }
                case 8 -> bst.printInOrder();
            }
        }

        sc.close();
        System.out.println("Bye 🚦");
    }
}
