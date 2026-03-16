package ie.atu.reservationsystemexam.exception;

public class ReservationNotFoundException extends RuntimeException {
  public ReservationNotFoundException(String reservationId) {
    super(reservationId);
  }
}
