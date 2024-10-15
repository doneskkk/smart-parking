package md.donesk.smartparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParkingSessionNotFoundException extends RuntimeException {
    public ParkingSessionNotFoundException(String message) {
        super(message);
    }
}
