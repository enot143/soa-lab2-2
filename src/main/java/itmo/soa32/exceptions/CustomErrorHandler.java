package itmo.soa32.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashSet;

@ControllerAdvice
public class CustomErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HashSet<?>> handleContraintViolationException(final ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(prepareMessage(exception));
    }

    private HashSet<?> prepareMessage(ConstraintViolationException exception) {
        HashSet<ErrorMessage> array = new HashSet<>();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            array.add(new ErrorMessage(cv.getMessage()));
        }
        return array;
    }
}
