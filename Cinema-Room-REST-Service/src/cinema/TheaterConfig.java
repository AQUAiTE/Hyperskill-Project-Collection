package cinema;

import cinema.model.Seat;
import cinema.model.Theater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TheaterConfig {
    private final int ROWS = 9;
    private final int COLUMNS = 9;

    @Bean
    public Theater createTheater() {
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                seats.add(new Seat(i, j));
            }
        }

        return new Theater(ROWS, COLUMNS, seats);
    }
}
