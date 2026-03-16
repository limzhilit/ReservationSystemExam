package ie.atu.reservationsystemexam.exception;

public class ReservationConflictException extends RuntimeException {
  public ReservationConflictException(String timeSlotAlreadyBooked) {
    super(timeSlotAlreadyBooked);
  }
}
