package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Ticket {
    private UUID token;
    private Seat seat;

    public Ticket(UUID token, Seat seat) {
        this.token = token;;
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    @JsonProperty (value = "ticket")
    public Seat getSeat() {
        return seat;
    }

}
