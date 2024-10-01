package cinema.service;

import cinema.model.Seat;
import cinema.model.Theater;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    public final Theater theater;

    public TheaterService(Theater theater) {
        this.theater = theater;
    }

    public Seat buySeat(int row, int col) throws IllegalStateException, IndexOutOfBoundsException {

        try {
            Seat seat = theater.getSeat(row, col);

            if (!seat.isAvailable()) {
                throw new IllegalStateException();
            }

            seat.setIsAvailable(false);
            theater.getAvailableSeats().remove(seat);

            return seat;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
}