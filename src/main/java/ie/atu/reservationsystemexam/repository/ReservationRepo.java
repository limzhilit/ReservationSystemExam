package ie.atu.reservationsystemexam.repository;

import ie.atu.reservationsystemexam.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

  List<Reservation> findByReservationDate(LocalDate reservationDate);

  List<Reservation> findByReservationDateBetween(LocalDate startDate, LocalDate endDate);

  List<Reservation> findByEquipment(String studentEmail);
}
