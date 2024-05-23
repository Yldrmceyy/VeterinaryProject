package dev.cey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateUpdateRequest {
    @NotNull(message = "Available Date id cannot be null.")
    @Positive(message = "Available Date id must be positive.")
    private Long id;
    @NotNull(message = "Available Date Date cannot be empty.")
    private LocalDate date;

}
