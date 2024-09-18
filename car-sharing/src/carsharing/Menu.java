package carsharing;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final String invalidInput = "Not a menu option. Please try again";
    private boolean isMainActive = true;
    private boolean isManagerActive = true;
    private boolean isChoosingCompany = true;
    private boolean isCompanyMenuActive = true;

    private DBManager db;
    private final Scanner scanner;
    private CompanyDAOImp companyDAO;
    private CarDAOImp carDAO;

    Menu (DBManager db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
        this.companyDAO = new CompanyDAOImp();
        this.carDAO = new CarDAOImp();
    }

    void runMenu() {
        isMainActive = true;
        while (isMainActive) {
            System.out.print("1. Log in as a manager\n0. Exit\n");
            int opt = Integer.parseInt(scanner.nextLine());

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
        isManagerActive = true;
        while (isManagerActive) {
            System.out.print("1. Company list\n2. Create a company\n0. Back\n");
            int opt = Integer.parseInt(scanner.nextLine());

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
        System.out.println("Choose the company:");
        for (Company company : companies) {
            System.out.printf("%d. %s\n", company.getId(), company.getName());
        }
        System.out.println("0. Back");

        int choice = -1;
        isChoosingCompany = true;
        while (isChoosingCompany) {
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                isChoosingCompany = false;
                System.out.println();
                showManagerMenu();
            } else if (choice <= companies.size() && choice > 0) {
                isChoosingCompany = false;
                showCompanyMenu(companies, choice - 1);
            } else {
                System.out.println("Company id is out of range. Select an id from the list above.");
            }

        }
    }

    private void createCompany() {
        System.out.println("\nEnter the company name:");
        String name = scanner.nextLine();
        companyDAO.createNewCompany(db, name);
        System.out.println("The company was created!\n");
    }

    private void showCompanyMenu(ArrayList<Company> companies, int choice) {
        isCompanyMenuActive = true;
        Company company = companies.get(choice);
        while (isCompanyMenuActive) {
            System.out.printf("\n'%s' company:\n", company.getName());
            System.out.print("1. Car list\n2. Create a car\n0. Back\n");

            int opt = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (opt) {
                case 1:
                    showCars(company.getId());
                    break;
                case 2:
                    createCar(company);
                    break;
                case 0:
                    showManagerMenu();
                    isCompanyMenuActive = false;
                    break;
                default:
                    System.out.println(invalidInput);
            }
        }
    }

    private void showCars(int companyID) {
        ArrayList<Car> cars = carDAO.getCompanyCars(db, companyID);
        if (!cars.isEmpty()) {
            System.out.println("Car list:");
        }

        int numeration = 1;
        for (Car car : cars) {
            System.out.printf("%d. %s\n", numeration, car.getName());
            numeration++;
        }
    }

    private void createCar(Company company) {
        System.out.println("Enter the car name:");
        String name = scanner.nextLine();
        carDAO.createNewCar(db, company.getId(), name);
        System.out.println("The car was added!");
    }

}
