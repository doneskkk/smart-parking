package md.donesk.smartparking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EndParkingRequest {

    @NotNull(message = "Session ID cannot be NULL")
    private Long sessionId;

}
