package md.donesk.smartparking.controller;

import jakarta.validation.Valid;
import md.donesk.smartparking.dto.EndParkingRequest;
import md.donesk.smartparking.dto.ParkingSessionRequest;
import md.donesk.smartparking.dto.StartParkingResponse;
import md.donesk.smartparking.service.ParkingSessionService;
import md.donesk.smartparking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/parking-sessions")
@Validated
public class ParkingSessionController {
    private final ParkingSessionService parkingSessionService;
    private final UserService userService;


    public ParkingSessionController(ParkingSessionService parkingSessionService, UserService userService) {
        this.parkingSessionService = parkingSessionService;
        this.userService = userService;
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
    public ResponseEntity<String> getCurrentDuration(@PathVariable Long sessionId) {
        return new ResponseEntity<>(parkingSessionService.getCurrentDuration(sessionId), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<StartParkingResponse>> getParkingSessionByUser(Authentication authentication) {
        return new ResponseEntity<>(userService.getParkingSessionByUser(authentication), HttpStatus.OK);
    }



}
