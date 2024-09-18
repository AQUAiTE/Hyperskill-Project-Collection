package carsharing;

import java.sql.*;
import java.util.ArrayList;

public class CompanyDAOImp implements CompanyDAO {
    @Override
    public ArrayList<Company> getAllCompanies(DBManager db) {
        ArrayList<Company> companies = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.getDbURL())){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.SELECT_ALL_COMPANIES);

            if (!rs.isBeforeFirst()) {
                System.out.println("The company list is empty!");
                return companies;
            }

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                Company company = new Company(id, name);
                companies.add(company);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return companies;
    }

    @Override
    public void createNewCompany(DBManager db, String name) {

        try (Connection conn = DriverManager.getConnection(db.getDbURL())) {
            PreparedStatement ps = conn.prepareStatement(Queries.CREATE_NEW_COMPANY);
            ps.setObject(1, name);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
