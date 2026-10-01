import java.util.ArrayList;
import java.util.Scanner;

public class FitnessMembershipSystem {

    static Scanner sc = new Scanner(System.in);

    static void displayPlans() {
        System.out.println("\n----- Membership Plans -----");
        System.out.println("1. Basic    - Rs. 1000 per month");
        System.out.println("2. Standard - Rs. 1500 per month");
        System.out.println("3. Premium  - Rs. 2500 per month");
    }

    static String getPlanName(int choice) {
        switch (choice) {
            case 1:
                return "Basic";
            case 2:
                return "Standard";
            case 3:
                return "Premium";
            default:
                return "Invalid";
        }
    }

    static int getPlanRate(int choice) {
        switch (choice) {
            case 1:
                return 1000;
            case 2:
                return 1500;
            case 3:
                return 2500;
            default:
                return 0;
        }
    }

    static String getCategoryName(int category) {
        if (category == 2) {
            return "Student";
        } else if (category == 3) {
            return "Senior Citizen";
        } else {
            return "Regular";
        }
    }

    static double calculateFee(int rate, int months) {
        return rate * months;
    }

    static double getDiscountPercent(int months, int category) {
        double discount = 0;

        if (months >= 12) {
            discount = 20;
        } else if (months >= 6) {
            discount = 10;
        } else if (months >= 3) {
            discount = 5;
        }

        if (category == 2) {
            discount = discount + 10;
        } else if (category == 3) {
            discount = discount + 15;
        }

        return discount;
    }

    static double applyDiscount(double fee, double discountPercent) {
        double discountAmount = fee * discountPercent / 100;
        return fee - discountAmount;
    }

    static void printSummary(String name, int age, String category, String plan,
                             int months, double fee, double discountPercent, double finalFee) {
        System.out.println("\n===== Membership Summary =====");
        System.out.println("Member Name    : " + name);
        System.out.println("Age            : " + age);
        System.out.println("Category       : " + category);
        System.out.println("Plan           : " + plan);
        System.out.println("Duration       : " + months + " month(s)");
        System.out.println("Total Fee      : Rs. " + fee);
        System.out.println("Discount       : " + discountPercent + "%");
        System.out.println("Final Fee      : Rs. " + finalFee);
        System.out.println("==============================");
    }

    public static void main(String[] args) {
        ArrayList<String> memberNames = new ArrayList<String>();
        ArrayList<Double> memberFees = new ArrayList<Double>();
        char again;

        System.out.println("===== Fitness Membership Management System =====");

        do {
            System.out.print("\nEnter member name: ");
            String name = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();

            System.out.println("Select category:");
            System.out.println("1. Regular");
            System.out.println("2. Student");
            System.out.println("3. Senior Citizen");
            System.out.print("Enter choice: ");
            int category = sc.nextInt();

            displayPlans();
            int planChoice;
            do {
                System.out.print("Select plan (1-3): ");
                planChoice = sc.nextInt();
                if (planChoice < 1 || planChoice > 3) {
                    System.out.println("Invalid plan. Try again.");
                }
            } while (planChoice < 1 || planChoice > 3);

            int months;
            do {
                System.out.print("Enter duration in months: ");
                months = sc.nextInt();
                if (months <= 0) {
                    System.out.println("Duration must be at least 1 month.");
                }
            } while (months <= 0);

            String planName = getPlanName(planChoice);
            int rate = getPlanRate(planChoice);
            String categoryName = getCategoryName(category);

            double fee = calculateFee(rate, months);
            double discountPercent = getDiscountPercent(months, category);
            double finalFee = applyDiscount(fee, discountPercent);

            printSummary(name, age, categoryName, planName, months, fee, discountPercent, finalFee);

            memberNames.add(name);
            memberFees.add(finalFee);

            System.out.print("\nRegister another member? (y/n): ");
            again = sc.next().charAt(0);
            sc.nextLine();

        } while (again == 'y' || again == 'Y');

        double totalCollection = 0;
        System.out.println("\n===== All Registered Members =====");
        for (int i = 0; i < memberNames.size(); i++) {
            System.out.println((i + 1) + ". " + memberNames.get(i) + " - Rs. " + memberFees.get(i));
            totalCollection = totalCollection + memberFees.get(i);
        }
        System.out.println("Total Members    : " + memberNames.size());
        System.out.println("Total Collection : Rs. " + totalCollection);
    }
}
