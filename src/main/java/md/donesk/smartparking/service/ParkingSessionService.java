package md.donesk.smartparking.service;

import md.donesk.smartparking.dto.StartParkingResponse;
import md.donesk.smartparking.dto.UserResponse;
import md.donesk.smartparking.enums.Sectors;
import md.donesk.smartparking.exception.ParkingSessionNotFoundException;
import md.donesk.smartparking.model.ParkingSession;
import md.donesk.smartparking.model.ParkingZone;
import md.donesk.smartparking.model.User;
import md.donesk.smartparking.repository.ParkingSessionRepository;
import md.donesk.smartparking.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final UserRepository userRepo;

    public ParkingSessionService(ParkingSessionRepository parkingSessionRepository, UserRepository userRepo) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.userRepo = userRepo;
    }

    public StartParkingResponse startParking(String licensePlate, String parkingZone, Authentication authentication) {


        User authenticatedUser = userRepo.findByUsername(authentication.getName()).get();
        ParkingSession parkingSession = parkingSessionRepository.save(ParkingSession.builder()
                .licensePlate(licensePlate)
                .parkingZone(ParkingZone.valueOf(parkingZone)).user(authenticatedUser)
                .startTime(LocalDateTime.now())
                .build());

        parkingSessionRepository.save(parkingSession);
//test git push
        return StartParkingResponse.builder()
                .id(parkingSession.getId())
                .cost(parkingSession.getCost())
                .licensePlate(parkingSession.getLicensePlate())
                .parkingZone(parkingSession.getParkingZone())
                .startTime(parkingSession.getStartTime())
                .endTime(parkingSession.getEndTime())
                .user(UserResponse.builder().id(authenticatedUser.getId())
                        .email(authenticatedUser.getEmail())
                        .phone(authenticatedUser.getPhone())
                        .name(authenticatedUser.getName())
                        .build())
                .build();
    }


    public String getCurrentDuration(Long sessionId) {

        ParkingSession parkingSession = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ParkingSessionNotFoundException("Session with id " + sessionId + " not found"));

        if(parkingSession.getEndTime() == null) {
        Duration duration = Duration.between(parkingSession.getStartTime(), LocalDateTime.now());
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);}
        else {
            return "Session is already finished";
        }

    }
    public void endParking(Long sessionId) {
        ParkingSession session = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ParkingSessionNotFoundException("Session with id " + sessionId + " not found"));
        session.setEndTime(LocalDateTime.now());
        session.setCost(calculateCost(session.getStartTime(), session.getEndTime(), String.valueOf(session.getParkingZone())));
        parkingSessionRepository.save(session);
    }

    private Double calculateCost(LocalDateTime start, LocalDateTime end, String zone) {
        long duration = ChronoUnit.MINUTES.between(start, end);
        double rate = getRateForZone(zone);
        return duration * rate;
    }

    private double getRateForZone(String zone) {
     try {
         return Sectors.valueOf(zone).getPrice();
     } catch (IllegalArgumentException e) {
         return 0;
     }
    }

    public List<StartParkingResponse> getParkingSessions() {

        List<ParkingSession> parkingSessions = parkingSessionRepository.findAllWithUsers();
        return parkingSessions.stream()
                .map(session -> StartParkingResponse.builder()
                        .id(session.getId())
                                .cost(session.getCost())
                        .licensePlate(session.getLicensePlate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .parkingZone(session.getParkingZone())
                        .user(UserResponse.convertToUserResponse(session.getUser()))
                        .build()
                        ).collect(Collectors.toList());
    }
}
