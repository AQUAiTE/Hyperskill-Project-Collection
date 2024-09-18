package carsharing;

class Queries {
    final static String CREATE_COMPANY_TABLE =  "CREATE TABLE IF NOT EXISTS COMPANY (" +
                                                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                                                "NAME VARCHAR(255) NOT NULL UNIQUE" +
                                                ");";

    final static String CREATE_CAR_TABLE =  "CREATE TABLE IF NOT EXISTS CAR (" +
                                            "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                                            "NAME VARCHAR(255) NOT NULL UNIQUE, " +
                                            "COMPANY_ID INT NOT NULL," +
                                            "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
                                            ");";

    final static String CREATE_NEW_COMPANY = "INSERT INTO COMPANY (name) VALUES (?);";

    final static String CREATE_NEW_CAR = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES (?, ?);";

    final static String SELECT_ALL_COMPANIES = "SELECT * FROM COMPANY;";

    final static String SELECT_ALL_CARS = "SELECT * FROM CAR WHERE COMPANY_ID = ?;";
}
