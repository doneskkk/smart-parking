package md.donesk.smartparking.dto;

import lombok.Data;

@Data
public class ParkingSessionRequest {

    private String licensePlate;
    private String parkingZone;
}
