package carsharing;

import org.h2.command.Prepared;

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
