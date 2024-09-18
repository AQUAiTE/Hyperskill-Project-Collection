package carsharing;

import java.util.ArrayList;

public interface CarDAO {
    ArrayList<Car> getCompanyCars(DBManager db, int id);
    void createNewCar(DBManager db, int companyID, String name);
}
