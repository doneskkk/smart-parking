package md.donesk.smartparking.controller;

import md.donesk.smartparking.dto.EndParkingRequest;
import md.donesk.smartparking.dto.ParkingSessionRequest;
import md.donesk.smartparking.model.ParkingSession;
import md.donesk.smartparking.model.ParkingZone;
import md.donesk.smartparking.service.ParkingSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-sessions")
public class ParkingSessionController {
    private final ParkingSessionService parkingSessionService;


    public ParkingSessionController(ParkingSessionService parkingSessionService) {
        this.parkingSessionService = parkingSessionService;
    }

    @PostMapping("/start")
    public ResponseEntity<ParkingSession> startParking(@RequestBody ParkingSessionRequest parkingSessionRequest) {

       ParkingSession parkingSession =  parkingSessionService.startParking(parkingSessionRequest.getLicensePlate(), parkingSessionRequest.getParkingZone());
       return ResponseEntity.ok(parkingSession);
    }

    @PostMapping("/end")
    public ResponseEntity<String> endParking(@RequestBody EndParkingRequest endParkingRequest) {
        parkingSessionService.endParking(endParkingRequest.getSessionId());
        return ResponseEntity.ok("Parking session with id "+ endParkingRequest.getSessionId()+" ended");
    }

    @GetMapping
    public ResponseEntity<List<ParkingSession>> getParkingSessions() {
        return new ResponseEntity<>(parkingSessionService.getParkingSessions(), HttpStatus.OK);
    }


}
