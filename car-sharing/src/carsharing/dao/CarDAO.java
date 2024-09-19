package carsharing.dao;

import carsharing.database.DBManager;
import carsharing.model.Car;

import java.util.ArrayList;

public interface CarDAO {
    ArrayList<Car> getCompanyCars(DBManager db, int id);
    ArrayList<Car> getAvailableCars(DBManager db, int companyID);
    String[] getCarByID(DBManager db, int carID);
    void createNewCar(DBManager db, int companyID, String name);
}
