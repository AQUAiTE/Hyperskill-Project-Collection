package carsharing;

class Queries {
    final static String CREATE_COMPANY_TABLE = "CREATE TABLE IF NOT EXISTS COMPANY " +
            "(ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
            " NAME VARCHAR(255) NOT NULL UNIQUE);";

    final static String SELECT_ALL_COMPANIES = "SELECT * FROM COMPANY;";

    final static String CREATE_NEW_COMPANY = "INSERT INTO COMPANY (name) VALUES (?);";
}
