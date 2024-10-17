package md.donesk.smartparking.dto;

import lombok.Builder;
import lombok.Data;
import md.donesk.smartparking.model.ParkingZone;

import java.time.LocalDateTime;

@Data
@Builder
public class StartParkingResponse {

    private Long id;

    private String licensePlate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ParkingZone parkingZone;

    private Double cost;

    private UserResponse user;
}
