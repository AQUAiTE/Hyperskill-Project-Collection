package cinema.controller;

import cinema.model.Theater;
import cinema.model.Seat;
import cinema.model.Ticket;

import cinema.service.TheaterService;

import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @GetMapping("/seats")
    public ResponseEntity<Theater> getAvailableSeats() {
        return new ResponseEntity<>(theaterService.theater, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();

        Ticket boughtTicket = theaterService.buyTicket(row, column);
        return new ResponseEntity<>(boughtTicket, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody TokenRequest token) {
        UUID tokenID = token.getToken();
        Ticket returnedTicket = theaterService.returnTicket(tokenID);
        Map<String, Seat> res = new HashMap<>();
        res.put("ticket", returnedTicket.getSeat());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> handleIndexOutOfBoundsException() {
        Map<String, String> outOfBoundsResponse = new HashMap<>();
        outOfBoundsResponse.put("error", "The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(outOfBoundsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException() {
        Map<String, String> alreadyPurchasedResponse = new HashMap<>();
        alreadyPurchasedResponse.put("error", "The ticket has been already purchased!");
        return new ResponseEntity<>(alreadyPurchasedResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidTokenException() {
        Map<String, String> invalidTokenResponse = new HashMap<>();
        invalidTokenResponse.put("error", "Wrong token!");
        return new ResponseEntity<>(invalidTokenResponse, HttpStatus.BAD_REQUEST);
    }
}

class TokenRequest {
    private UUID token;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
