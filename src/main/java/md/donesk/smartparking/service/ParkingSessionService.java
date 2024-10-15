package md.donesk.smartparking.service;

import jakarta.persistence.EntityNotFoundException;
import md.donesk.smartparking.exception.ParkingSessionNotFoundException;
import md.donesk.smartparking.model.ParkingSession;
import md.donesk.smartparking.model.ParkingZone;
import md.donesk.smartparking.repository.ParkingSessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;

    public ParkingSessionService(ParkingSessionRepository parkingSessionRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
    }

    public ParkingSession startParking(String licensePlate, String parkingZone) {
        ParkingSession session = new ParkingSession();
        session.setLicensePlate(licensePlate);
        session.setStartTime(LocalDateTime.now());
        session.setParkingZone(ParkingZone.valueOf(parkingZone));
        return parkingSessionRepository.save(session);
    }

    public ParkingSession endParking(Long sessionId) {
        ParkingSession session = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ParkingSessionNotFoundException("Session with id " + sessionId + " not found"));
        session.setEndTime(LocalDateTime.now());
        session.setCost(calculateCost(session.getStartTime(), session.getEndTime(), String.valueOf(session.getParkingZone())));
        return parkingSessionRepository.save(session);
    }

    private Double calculateCost(LocalDateTime start, LocalDateTime end, String zone) {
        long duration = ChronoUnit.MINUTES.between(start, end);
        double rate = getRateForZone(zone);
        return duration * rate;
    }

    private double getRateForZone(String zone) {
        return switch (zone) {
            case "ZONE1" -> 5.0;
            case "ZONE2" -> 10.0;
            case "ZONE3" -> 20.0;
            default -> 0;
        };
    }

    public List<ParkingSession> getParkingSessions() {
        return parkingSessionRepository.findAll();
    }
}
