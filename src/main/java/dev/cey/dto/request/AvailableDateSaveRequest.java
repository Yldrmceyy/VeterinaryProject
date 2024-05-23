package dev.cey.dto.request;

import dev.cey.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateSaveRequest {
    @NotNull(message = "Available Date cannot be empty")
    private LocalDate date;
    @NotNull(message = "Doctor id cannot be empty.")

    // private Long doctorId;
    private Doctor doctor;
}