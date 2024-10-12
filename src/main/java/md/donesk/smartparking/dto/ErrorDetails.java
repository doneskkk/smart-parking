package md.donesk.smartparking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {

    private final String message;
    private final String details;

}
