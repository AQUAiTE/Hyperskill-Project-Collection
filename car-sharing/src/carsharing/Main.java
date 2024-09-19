package carsharing;

import carsharing.database.DBManager;

public class Main {
    public static void main(String[] args) {
        DBManager h2DB = new DBManager(args);
        Menu menu = new Menu(h2DB);
        menu.runMenu();
    }

}