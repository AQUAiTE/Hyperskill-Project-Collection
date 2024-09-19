package carsharing.model;

public class Customer {
    private int id;
    private String name;
    private int rentalID;

    public Customer(int id, String name, int rentalID) {
        this.id = id;
        this.name = name;
        this.rentalID = -1;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getRentalID() {
        return this.rentalID;
    }
}
