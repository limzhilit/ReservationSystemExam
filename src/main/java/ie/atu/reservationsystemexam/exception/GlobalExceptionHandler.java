package ie.atu.reservationsystemexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>>
  handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(
            toMap(
                FieldError::getField,
                FieldError::getDefaultMessage
            )
        );
    return ResponseEntity.badRequest().body(errors);
  }
  /*
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidation(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  */

  @ExceptionHandler(ReservationConflictException.class)
  public ResponseEntity<String>
  handleConflict(ReservationConflictException ex) {

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ex.getMessage());
  }

  @ExceptionHandler(ReservationNotFoundException.class)
  public ResponseEntity<String>
  handleNotFound(ReservationNotFoundException ex) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

}


// ex.getBindingResult().getFieldErrors() → list of validation errors
// .stream() → convert list into Java Stream
// .collect(toMap(...)) → convert stream to Map
// FieldError::getField → key = field name
// FieldError::getDefaultMessage → value = error message