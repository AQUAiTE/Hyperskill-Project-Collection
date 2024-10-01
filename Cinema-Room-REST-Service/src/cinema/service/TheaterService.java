package cinema.service;

import cinema.model.Seat;
import cinema.model.Theater;
import cinema.model.Ticket;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TheaterService {
    public final Theater theater;

    public TheaterService(Theater theater) {
        this.theater = theater;
    }

    public Ticket buyTicket(int row, int col) throws IllegalStateException, IndexOutOfBoundsException {

        try {
            Seat seat = theater.getSeat(row, col);

            if (!seat.isAvailable()) {
                throw new IllegalStateException();
            }

            UUID token = UUID.randomUUID();
            Ticket ticket = new Ticket(token, seat);

            theater.addNewTicket(token, ticket);
            seat.setIsAvailable(false);
            theater.getAvailableSeats().remove(seat);

            return ticket;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }

    }

    public Ticket returnTicket(UUID token) throws IllegalArgumentException {
        try {
            if (!theater.isTokenInMap(token)) {
                throw new IllegalArgumentException();
            }

            Ticket ticket = theater.getTicket(token);
            Seat ticketSeat = ticket.getSeat();

            ticketSeat.setIsAvailable(true);
            int seatPos = theater.getSeatListPosition(ticketSeat.getRow(), ticketSeat.getColumn());
            theater.getAvailableSeats().add(seatPos, ticketSeat);
            theater.removeTicket(token);

            return ticket;
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }
}