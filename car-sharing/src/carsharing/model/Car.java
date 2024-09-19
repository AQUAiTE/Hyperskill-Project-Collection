package carsharing.model;

public class Car {
    private int id;
    private String name;
    private int companyID;

    public Car(int id, String name, int companyID) {
        this.id = id;
        this.name = name;
        this.companyID = companyID;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCompanyId() {
        return this.companyID;
    }
}
