package be.technocite.ecar.car.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CarConflictException extends RuntimeException{

    public CarConflictException(String message) {
        super(message);
    }
}
