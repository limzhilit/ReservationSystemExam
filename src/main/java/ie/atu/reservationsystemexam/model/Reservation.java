package ie.atu.reservationsystemexam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationId;

  @NotBlank(message = "equipmentTag is required")
  private String equipmentTag;

  @NotBlank(message = "studentEmail is required")
  @Email(message = "studentEmail must be a valid email address")
  private String studentEmail;

  @NotNull(message = "reservationDate is required")
  private LocalDate reservationDate;

  @Min(value = 0, message = "startHour must be between 0 and 23" )
  @Max(value = 23, message = "startHour must be between 0 and 23" )
  private int startHour;

  @Min(value = 0, message = "durationHour must be between 0 and 24" )
  @Max(value = 24, message = "durationHour must be between 0 and 24" )
  private int durationHours;




}
