package carsharing.dao;

import carsharing.database.DBManager;
import carsharing.Queries;
import carsharing.model.Car;

import java.sql.*;
import java.util.ArrayList;

public class CarDAOImp implements CarDAO {
    @Override
    public ArrayList<Car> getCompanyCars(DBManager db, int id) {
        ArrayList<Car> cars = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.SELECT_ALL_CARS);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("The car list is empty!");
                return cars;
            }

            while (rs.next()) {
                int carID = rs.getInt("ID");
                String name = rs.getString("NAME");
                int companyID = rs.getInt("COMPANY_ID");
                Car car = new Car(carID, name, companyID);
                cars.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public ArrayList<Car> getAvailableCars(DBManager db, int companyID) {
        ArrayList<Car> cars = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.SELECT_AVAILABLE_CARS);
            ps.setObject(1, companyID);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("The car list is empty!");
                return cars;
            }

            while (rs.next()) {
                int carID = rs.getInt("CAR.ID");
                String name = rs.getString("CAR.NAME");
                Car car = new Car(carID, name, companyID);
                cars.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public String[] getCarByID(DBManager db, int carID) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.SELECT_CAR_BY_ID);
            ps.setObject(1, carID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("CAR.NAME");
                String company = rs.getString("COMPANY.NAME");
                return new String[]{name, company};
            } else {
                // Debug only
                System.out.println("Something went wrong when getting the car by ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[0];
    }

    @Override
    public void createNewCar(DBManager db, int companyID, String name) {
        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.CREATE_NEW_CAR);
            ps.setObject(1, name);
            ps.setObject(2, companyID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
