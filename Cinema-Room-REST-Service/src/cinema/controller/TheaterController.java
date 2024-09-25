package cinema.controller;

import cinema.model.Theater;
import cinema.model.Seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {
    @Autowired
    private Theater theater;

    @GetMapping("/seats")
    public ResponseEntity<Theater> getAvailableSeats() {
        return new ResponseEntity<>(theater, HttpStatus.OK);
    }
}
