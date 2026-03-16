package ie.atu.reservationsystemexam.controller;

import ie.atu.reservationsystemexam.model.Reservation;
import ie.atu.reservationsystemexam.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

  private ReservationService reservationService;

  @PostMapping
  public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {
    Reservation saved = reservationService.addReservation(reservation);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping
  public ResponseEntity<List<Reservation>> getAll() {
    return ResponseEntity.ok(reservationService.getAllReservations());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getById(@PathVariable Long id) {
    return ResponseEntity.ok(reservationService.getReservationById(id));
  }

  @GetMapping("/date/{date}")
  public ResponseEntity<List<Reservation>> getByDate(@PathVariable LocalDate date) {
    return ResponseEntity.ok(reservationService.getReservationsByDate(date));
  }

  @GetMapping("/dateAndTag/{date}/{tag}")
  public ResponseEntity<List<Reservation>> getByDate(
      @PathVariable LocalDate date,
      @PathVariable String tag
  ) {
    return ResponseEntity.ok(reservationService.getReservationsByTagAndDate(tag, date));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<List<Reservation>> getByEmail(@PathVariable String email) {
    return ResponseEntity.ok(reservationService.getReservationsByEmail(email));
  }




}
