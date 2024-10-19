package md.donesk.smartparking.controller;

import jakarta.validation.Valid;
import md.donesk.smartparking.dto.EndParkingRequest;
import md.donesk.smartparking.dto.ParkingSessionRequest;
import md.donesk.smartparking.dto.StartParkingResponse;
import md.donesk.smartparking.service.ParkingSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/parking-sessions")
@Validated
public class ParkingSessionController {
    private final ParkingSessionService parkingSessionService;


    public ParkingSessionController(ParkingSessionService parkingSessionService) {
        this.parkingSessionService = parkingSessionService;
    }

    @PostMapping("/start")
    public ResponseEntity<StartParkingResponse> startParking(@Valid @RequestBody ParkingSessionRequest parkingSessionRequest, Authentication authentication) {

        StartParkingResponse startParkingResponse = parkingSessionService.startParking(parkingSessionRequest.getLicensePlate(), parkingSessionRequest.getParkingZone(), authentication);

        return ResponseEntity.ok(startParkingResponse);
    }


    @PostMapping("/end")
    public ResponseEntity<String> endParking(@Valid @RequestBody EndParkingRequest endParkingRequest) {
        parkingSessionService.endParking(endParkingRequest.getSessionId());
        return ResponseEntity.ok("Parking session with id "+ endParkingRequest.getSessionId()+" ended");
    }

    @GetMapping
    public ResponseEntity<List<StartParkingResponse>> getParkingSessions() {
        return new ResponseEntity<>(parkingSessionService.getParkingSessions(), HttpStatus.OK);
    }

    @GetMapping("/duration/{sessionId}")
    public ResponseEntity<Duration> getCurrentDuration(@PathVariable Long sessionId) {
        return new ResponseEntity<>(parkingSessionService.getCurrentDuration(sessionId), HttpStatus.OK);
    }


}
