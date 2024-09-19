package carsharing.dao;

import carsharing.database.DBManager;
import carsharing.model.Company;

import java.util.ArrayList;

public interface CompanyDAO {
    ArrayList<Company> getAllCompanies(DBManager db);
    void createNewCompany(DBManager db, String name);
}
