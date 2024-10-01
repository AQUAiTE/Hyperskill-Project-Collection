package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private int rows;
    private int columns;

    private List<Seat> seats;
    private List<Seat> availableSeats;

    public Theater(int rows, int columns, List<Seat> seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
        this.availableSeats = new ArrayList<>(this.seats);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @JsonIgnore
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @JsonProperty(value = "seats")
    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Seat getSeat(int row, int col) throws IndexOutOfBoundsException {
        try {
            int seatPos = getSeatListPosition(row, col);
            return this.seats.get(seatPos);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    private boolean isValidPosition(int row, int col) {
        if (row <= 0 || col <= 0 || row > rows || col > columns) {
            return false;
        }

        return true;
    }

    private int getSeatListPosition(int row, int col) throws IndexOutOfBoundsException {
        if (!isValidPosition(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return ((row - 1) * columns + (col - 1));
    }

}
