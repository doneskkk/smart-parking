package md.donesk.smartparking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParkingSessionRequest {

    @NotBlank(message = "License plate cannot be blank")
    private String licensePlate;

    @NotBlank(message = "Please choose the parking zone")
    private String parkingZone;
}
