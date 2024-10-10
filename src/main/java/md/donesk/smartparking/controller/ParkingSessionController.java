package md.donesk.smartparking.controller;

import md.donesk.smartparking.repository.ParkingSessionRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingSessionController {
    private final ParkingSessionRepository parkingSessionRepository;

    public ParkingSessionController(ParkingSessionRepository parkingSessionRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
    }
}
