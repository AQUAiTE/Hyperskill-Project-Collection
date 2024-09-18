package carsharing;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final String invalidInput = "Not a menu option. Please try again";
    private boolean isMainActive = true;
    private boolean isManagerActive = true;

    private DBManager db;
    private final Scanner scanner;
    private CompanyDAOImp companyDAO;

    Menu (DBManager db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
        this.companyDAO = new CompanyDAOImp();
    }

    void runMenu() {
        int opt = -1;
        while (isMainActive) {
            System.out.print("1. Log in as a manager\n0. Exit\n");
            opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    System.out.println();
                    showManagerMenu();
                    break;
                case 0:
                    isMainActive = false;
                    break;
                default:
                    System.out.println(invalidInput);
            }
        }
    }

    private void showManagerMenu() {
        int opt = -1;
        while (isManagerActive) {
            System.out.print("1. Company list\n2. Create a company\n0. Back\n");
            opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    showCompanyList();
                    break;
                case 2:
                    createCompany();
                    break;
                case 0:
                    System.out.println();
                    runMenu();
                    isManagerActive = false;
                    break;
                default:
                    System.out.println(invalidInput);
            }
        }
    }

    private void showCompanyList() {
        ArrayList<Company> companies = companyDAO.getAllCompanies(db);

        if (companies.isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("Company list:");
        for (Company company : companies) {
            System.out.printf("%d. %s\n", company.getId(), company.getName());
        }

        System.out.println();
    }

    private void createCompany() {
        System.out.println("\nEnter the company name:");
        String name = scanner.nextLine();
        companyDAO.createNewCompany(db, name);

        System.out.println("The company was created!\n");
    }
}
