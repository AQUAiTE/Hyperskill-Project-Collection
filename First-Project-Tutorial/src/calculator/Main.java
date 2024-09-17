package calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Static pricing for shop items
        int bubblegum = 202;
        int toffee = 118;
        int iceCream = 2250;
        int milkChocolate = 1680;
        int doughnut = 1075;
        int pancake = 80;
        int total = bubblegum + toffee + iceCream + milkChocolate + doughnut + pancake;

        // Printing the shop income
        System.out.println("Earned amount:");
        System.out.println("Bubblegum: $" + bubblegum);
        System.out.println("Toffee: $" + toffee);
        System.out.println("Ice cream: $" + iceCream);
        System.out.println("Milk chocolate: $" + milkChocolate);
        System.out.println("Doughnut: $" + doughnut);
        System.out.println("Pancake: $"+ pancake);
        System.out.println();
        System.out.printf("Income: $%d\n", total);

        // Get user input for expenses
        Scanner scanner = new Scanner(System.in);
        System.out.println("Staff expenses:");
        int staffExpenses = scanner.nextInt();
        System.out.println("Other expenses:");
        int otherExpenses = scanner.nextInt();

        // Output net income
        System.out.printf("Net income: $%d\n", total - staffExpenses - otherExpenses);
    }

}