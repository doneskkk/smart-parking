package md.donesk.smartparking.service;

import jakarta.persistence.EntityNotFoundException;
import md.donesk.smartparking.model.ParkingSession;
import md.donesk.smartparking.repository.ParkingSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        session.setParkingZone(parkingZone);
        // логика для расчета стоимости парковки по зоне
        return parkingSessionRepository.save(session);
    }

    public ParkingSession endParking(Long sessionId) {
        ParkingSession session = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found"));
        session.setEndTime(LocalDateTime.now());
        session.setCost(calculateCost(session.getStartTime(), session.getEndTime(), session.getParkingZone()));
        return parkingSessionRepository.save(session);
    }

    private Double calculateCost(LocalDateTime start, LocalDateTime end, String zone) {
        long duration = ChronoUnit.MINUTES.between(start, end);
        double rate = getRateForZone(zone);
        return duration * rate;
    }

    private double getRateForZone(String zone) {
        // логика для получения тарифа зоны
        return 2.0; // например, 2 единицы за минуту
    }
}
