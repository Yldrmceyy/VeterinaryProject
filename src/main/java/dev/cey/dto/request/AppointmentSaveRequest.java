package dev.cey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentSaveRequest {
    @NotNull(message = "Appointment Date and Time cannot be empty")
    private LocalDateTime appointmentDateTime;
    @NotNull(message = "Animal id cannot be empty.")
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long animalId;
    @NotNull(message = "Animal id cannot be empty.")
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long doctorId;

}
