package ie.atu.reservationsystemexam.controller;

import ie.atu.reservationsystemexam.model.Reservation;
import ie.atu.reservationsystemexam.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
