package carsharing;

import carsharing.dao.CarDAOImp;
import carsharing.dao.CompanyDAOImp;
import carsharing.dao.CustomerDAOImp;
import carsharing.database.DBManager;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final String invalidInput = "Not a menu option. Please try again";
    private boolean isChoosingCompany = true;
    private boolean isRentingCar = true;

    private final DBManager db;
    private final Scanner scanner;
    private final CompanyDAOImp companyDAO;
    private final CarDAOImp carDAO;
    private final CustomerDAOImp customerDAO;

    Menu (DBManager db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
        this.companyDAO = new CompanyDAOImp();
        this.carDAO = new CarDAOImp();
        this.customerDAO = new CustomerDAOImp();
    }

    void runMenu() {
        boolean isMainActive = true;
        while (isMainActive) {
            System.out.print("1. Log in as a manager\n2. Log in as a customer\n3. Create a customer\n0. Exit\n");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    System.out.println();
                    showManagerMenu();
                    break;
                case 2:
                    System.out.println();
                    showCustomerList();
                    break;
                case 3:
                    System.out.println();
                    askCustomerName();
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
        boolean isManagerActive = true;
        while (isManagerActive) {
            System.out.print("1. Company list\n2. Create a company\n0. Back\n");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    ArrayList<Company> companies = showCompanyList();

                    if (!companies.isEmpty()) {
                        chooseCompany(companies);
                    }
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

    private ArrayList<Company> showCompanyList() {
        ArrayList<Company> companies = companyDAO.getAllCompanies(db);

        if (companies.isEmpty()) {
            return companies;
        }

        System.out.println("\nChoose the company:");
        for (Company company : companies) {
            System.out.printf("%d. %s\n", company.getId(), company.getName());
        }
        System.out.println("0. Back");

        return companies;
    }

    private void chooseCompany(ArrayList<Company> companies) {
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
                showCompanyMenu(companies, choice);
            } else {
                System.out.println("Company id is out of range. Select an id from the list above.");
            }

        }
    }

    private int chooseCompanyID(ArrayList<Company> companies) {
        int choice = -1;
        isChoosingCompany = true;
        while (isChoosingCompany) {
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                isChoosingCompany = false;
                System.out.println();
            } else if (choice <= companies.size() && choice > 0) {
                isChoosingCompany = false;
            } else {
                System.out.println("Company id is out of range. Select an id from the list above.");
            }

        }

        return choice;
    }

    private void showCompanyMenu(ArrayList<Company> companies, int choice) {
        boolean isCompanyMenuActive = true;
        Company company = companies.get(choice - 1);
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

    private void createCompany() {
        System.out.println("\nEnter the company name:");
        String name = scanner.nextLine();
        companyDAO.createNewCompany(db, name);
        System.out.println("The company was created!\n");
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

    private void askCustomerName() {
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        customerDAO.createNewCustomer(db, name);
        System.out.println("The customer was added!\n");
    }

    private void showCustomerList() {
        ArrayList<Customer> customers = customerDAO.getAllCustomers(db);

        if (customers.isEmpty()) {
            return;
        }

        System.out.println("Customer list:");
        for (Customer customer : customers) {
            System.out.printf("%d. %s\n", customer.getID(), customer.getName());
        }
        System.out.println("0. Back");

        int choice = -1;
        boolean isChoosingCustomer = true;
        int customerID;
        while (isChoosingCustomer) {
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                isChoosingCustomer = false;
                System.out.println();
                runMenu();
            } else if (choice <= customers.size() && choice > 0) {
                customerID = customers.get(choice - 1).getID();
                isChoosingCustomer = false;
                System.out.println();
                showCustomerMenu(customerID);
            } else {
                System.out.println("Company id is out of range. Select an id from the list above.");
            }

        }
    }


    private void showCustomerMenu(int customerID) {
        boolean isCustomerActive = true;
        while (isCustomerActive) {
            System.out.println("1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back");
            int opt = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (opt) {
                case 1:
                    if (!customerDAO.checkAlreadyRented(db, customerID)) {
                        rentCar(customerID);
                    } else {
                        System.out.println("You've already rented a car!\n");
                    }
                    break;
                case 2:
                    returnCar(customerID);
                    break;
                case 3:
                    seeRentedCar(customerID);
                    break;
                case 0:
                    runMenu();
                    isCustomerActive = false;
                    break;
                default:
                    System.out.println(invalidInput);
            }
        }
    }

    private void rentCar(int customerID) {
        isRentingCar = true;
        System.out.println("Choose a company:");
        ArrayList<Company> companies = showCompanyList();
        int companyID = chooseCompanyID(companies);
        if (companyID == 0) {
            isRentingCar = false;
            showCustomerMenu(customerID);
        }

        showAvailableCars(companyID, customerID);

    }

    private void showAvailableCars(int companyID, int customerID) {
        ArrayList<Car> cars = carDAO.getAvailableCars(db, companyID);
        int numeration = 1;

        System.out.println("\nChoose a car:");
        for (Car car : cars) {
            System.out.printf("%d. %s\n", numeration, car.getName());
            numeration++;
        }
        System.out.println("0. Back");

        selectCar(cars, customerID);
    }

    private void selectCar(ArrayList<Car> cars, int customerID) {
        isRentingCar = true;
        while (isRentingCar) {
            int opt = Integer.parseInt(scanner.nextLine());

            if (opt < 0 || opt > cars.size()) {
                System.out.println();
                System.out.println(invalidInput);
                System.out.println();
            } else if (opt == 0) {
                isRentingCar = false;
                rentCar(customerID);
            } else {
                Car car = cars.get(opt - 1);
                customerDAO.updateCustomerRental(db, car.getID(), customerID);
                System.out.printf("\nYou rented '%s'\n\n", car.getName());
                isRentingCar = false;
                showCustomerMenu(customerID);
            }
        }
    }

    private void returnCar(int customerID) {
        if (!customerDAO.checkAlreadyRented(db, customerID)) {
            System.out.println("You didn't rent a car!\n");
            return;
        }

        customerDAO.updateCustomerReturn(db, customerID);
        System.out.println("You've returned a rented car!\n");
    }

    private void seeRentedCar(int customerID) {
        if (!customerDAO.checkAlreadyRented(db, customerID)) {
            System.out.println("You didn't rent a car!\n");
            return;
        }
        int carID = customerDAO.getRentedCarID(db, customerID);

        String[] carInfo = carDAO.getCarByID(db, carID);
        System.out.printf("\nYour rented car:\n%s\nCompany:\n%s\n\n", carInfo[0], carInfo[1]);
        showCustomerMenu(customerID);
    }

}
