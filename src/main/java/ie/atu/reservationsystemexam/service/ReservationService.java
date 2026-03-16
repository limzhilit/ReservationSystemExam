package ie.atu.reservationsystemexam.service;

import ie.atu.reservationsystemexam.exception.ReservationConflictException;
import ie.atu.reservationsystemexam.exception.ReservationNotFoundException;
import ie.atu.reservationsystemexam.model.Reservation;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

  private List<Reservation> reservations = new ArrayList<>();
  private long nextId = 1;

  public Reservation addReservation(@Valid Reservation reservation) {
    reservation.setReservationId(nextId++);
    for (Reservation existing : reservations) {
      if (existing.getEquipmentTag().equals(reservation.getEquipmentTag()) &&
          existing.getReservationDate().equals(reservation.getReservationDate())) {

        int existingStart = existing.getStartHour();
        int existingEnd = existingStart + existing.getDurationHours();

        int newStart = reservation.getStartHour();
        int newEnd = newStart + reservation.getDurationHours();

        if (newStart < existingStart || newEnd > existingEnd) {
          reservations.add(reservation);
          throw new ReservationConflictException("Time slot already booked");
        }
      }
    }
    reservations.add(reservation);
    return reservation;
  }

  public List<Reservation> getAllReservations() {
    return reservations;
  }

  public Reservation getReservationById(Long id) {
    for (Reservation reservation: reservations) {
      if (reservation.getReservationId().equals(id)) {
        return reservation;
      }
    }
    throw new ReservationNotFoundException("Reservation not found");
  }
}
