package cinema.model;

public class Stats {
    private int income;
    private int available;
    private int purchased;

    public Stats() {
        this.income = 0;
        this.available = 81;
        this.purchased = 0;
    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void increaseIncome(int ticketPrice) {
        income += ticketPrice;
    }

    public void decreaseIncome(int ticketPrice) {
        income -= ticketPrice;
    }

    public void incrementAvailable() {
        available++;
    }

    public void decrementAvailable() {
        available--;
    }

    public void incrementPurchased() {
        purchased++;
    }

    public void decrementPurchased() {
        purchased--;
    }

}
