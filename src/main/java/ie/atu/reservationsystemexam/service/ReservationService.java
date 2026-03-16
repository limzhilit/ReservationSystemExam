package ie.atu.reservationsystemexam.service;

import ie.atu.reservationsystemexam.exception.ReservationConflictException;
import ie.atu.reservationsystemexam.exception.ReservationNotFoundException;
import ie.atu.reservationsystemexam.model.Reservation;
import ie.atu.reservationsystemexam.repository.ReservationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

  final private ReservationRepo reservationRepo;

  public Reservation addReservation(Reservation reservation) {

    List<Reservation> reservations = reservationRepo.findByEquipmentTagAndReservationDate(reservation.getEquipmentTag(), reservation.getReservationDate());
    for (Reservation existing : reservations) {
      int existingStart = existing.getStartHour();
      int existingEnd = existingStart + existing.getDurationHours();

      int newStart = reservation.getStartHour();
      int newEnd = newStart + reservation.getDurationHours();

      if (newStart < existingEnd || newEnd > existingStart) {
        throw new ReservationConflictException("Time slot already booked");
      }

    }
    reservationRepo.save(reservation);
    return reservation;
  }

  public List<Reservation> getAllReservations() {
    return reservationRepo.findAll();
  }

  public Reservation getReservationById(Long id) {
    return reservationRepo.findById(id).orElseThrow(
        () -> new ReservationNotFoundException("Reservation not found")
    );
  }

  public List<Reservation> getReservationsByDate(LocalDate reservationDate) {
    return reservationRepo.findByReservationDate(reservationDate);
  }


  public List<Reservation> getReservationsByTagAndDate(String equipmentTag, LocalDate reservationDate) {
    return reservationRepo.findByEquipmentTagAndReservationDate(equipmentTag, reservationDate);
  }

  public List<Reservation> getReservationsByEmail(String email) {
    return reservationRepo.findByStudentEmail(email);
  }
}
