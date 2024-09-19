package carsharing.dao;

import carsharing.database.DBManager;
import carsharing.model.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<Customer> getAllCustomers(DBManager db);
    boolean checkAlreadyRented(DBManager db, int customerID);
    int getRentedCarID(DBManager db, int customerID);
    void createNewCustomer(DBManager db, String name);
    void updateCustomerRental(DBManager db, int carID, int customerID);
    void updateCustomerReturn(DBManager db, int customerID);
}
