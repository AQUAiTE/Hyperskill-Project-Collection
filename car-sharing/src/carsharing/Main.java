package carsharing;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBManager h2DB = new DBManager(args);
        Menu menu = new Menu(h2DB);
        menu.runMenu();

        try {
            h2DB.cleanup();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}