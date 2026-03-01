package module3_performance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Module 3 Menu ===");
            System.out.println("1) Run performance analysis");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String input = sc.nextLine().trim();

            if (input.equals("1")) {
                PerformanceAnalyzer.run();
            } else if (input.equals("0")) {
                System.out.println("Exit ✅");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
