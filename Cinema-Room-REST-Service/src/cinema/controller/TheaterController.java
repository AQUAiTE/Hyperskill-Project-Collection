package cinema.controller;

import cinema.model.Theater;
import cinema.model.Seat;
import cinema.service.TheaterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

        Seat boughtSeat = theaterService.buySeat(row, column);
        return new ResponseEntity<>(boughtSeat, HttpStatus.OK);
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
}
