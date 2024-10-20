package md.donesk.smartparking.service;

import md.donesk.smartparking.dto.StartParkingResponse;
import md.donesk.smartparking.dto.UserResponse;
import md.donesk.smartparking.model.ParkingSession;
import md.donesk.smartparking.model.User;
import md.donesk.smartparking.repository.ParkingSessionRepository;
import md.donesk.smartparking.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final UserRepository userRepo;

    public UserService(ParkingSessionRepository parkingSessionRepository, UserRepository userRepo) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.userRepo = userRepo;
    }

    public List<StartParkingResponse> getParkingSessionByUser(Authentication authentication) {
        User authenticatedUser = userRepo.findByUsername(authentication.getName()).get();
        List<ParkingSession> parkingSessions = parkingSessionRepository.getParkingSessionByUserId(authenticatedUser.getId());
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
