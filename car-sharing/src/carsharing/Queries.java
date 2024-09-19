package carsharing;

public class Queries {
    public final static String CREATE_COMPANY_TABLE =  "CREATE TABLE IF NOT EXISTS COMPANY (" +
                                                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                                                "NAME VARCHAR(255) NOT NULL UNIQUE" +
                                                ");";

    public final static String CREATE_CAR_TABLE =  "CREATE TABLE IF NOT EXISTS CAR (" +
                                            "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                                            "NAME VARCHAR(255) NOT NULL UNIQUE, " +
                                            "COMPANY_ID INT NOT NULL," +
                                            "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
                                            ");";

    public final static String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                                                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                                                "NAME VARCHAR(255) NOT NULL UNIQUE, " +
                                                "RENTED_CAR_ID INT," +
                                                "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)" +
                                                ");";

    public final static String CREATE_NEW_COMPANY = "INSERT INTO COMPANY (name) VALUES (?);";

    public final static String CREATE_NEW_CAR = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES (?, ?);";

    public final static String CREATE_NEW_CUSTOMER = "INSERT INTO CUSTOMER (NAME) VALUES (?);";

    public final static String SELECT_AVAILABLE_CARS = "SELECT CAR.ID, CAR.NAME FROM CAR " +
                                                "WHERE CAR.COMPANY_ID = ?" +
                                                "AND CAR.ID NOT IN" +
                                                "(" +
                                                "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL" +
                                                ");";

    public final static String SELECT_CAR_BY_ID =  "SELECT CAR.NAME, COMPANY.NAME " +
                                            "FROM CAR " +
                                            "JOIN COMPANY ON CAR.COMPANY_ID = COMPANY.ID " +
                                            "WHERE CAR.ID = ?;";

    public final static String SELECT_ALL_CARS = "SELECT * FROM CAR WHERE COMPANY_ID = ?;";

    public final static String SELECT_CUSTOMER_RENTAL_STATUS = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = ?;";

    public final static String UPDATE_CUSTOMER_RENTAL = "UPDATE CUSTOMER " +
                                                 "SET RENTED_CAR_ID = ? " +
                                                 "WHERE ID = ?;";

    public final static String UPDATE_CUSTOMER_RETURN = "UPDATE CUSTOMER " +
                                                 "SET RENTED_CAR_ID = NULL " +
                                                 "WHERE ID = ?;";
}
