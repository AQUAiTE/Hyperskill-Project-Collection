package carsharing.dao;

import carsharing.database.DBManager;
import carsharing.Queries;
import carsharing.model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImp implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAllCustomers(DBManager db) {
        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER;");
            if (!rs.isBeforeFirst()) {
                System.out.println("The customer list is empty!\n");
                return customers;
            }

            while (rs.next()) {
                String name = rs.getString("NAME");
                int id = rs.getInt("ID");
                Customer customer = new Customer(id, name, -1);
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public boolean checkAlreadyRented(DBManager db, int customerID) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.SELECT_CUSTOMER_RENTAL_STATUS);
            ps.setObject(1, customerID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Object rentedID = rs.getObject("RENTED_CAR_ID");
                // We want to return true when they have a car rented
                return rentedID != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getRentedCarID(DBManager db, int customerID) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement("SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = ?;");
            ps.setObject(1, customerID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("RENTED_CAR_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public void createNewCustomer(DBManager db, String name) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.CREATE_NEW_CUSTOMER);
            ps.setObject(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomerRental(DBManager db, int carID, int customerID) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.UPDATE_CUSTOMER_RENTAL);
            ps.setObject(1, carID);
            ps.setObject(2, customerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomerReturn(DBManager db, int customerID) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.UPDATE_CUSTOMER_RETURN);
            ps.setObject(1, customerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
