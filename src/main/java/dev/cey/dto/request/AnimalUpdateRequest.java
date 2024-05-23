package dev.cey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalUpdateRequest {
    @Positive(message = "ID must be positive.")
    @NotNull(message = "Animal ID cannot be empty.")
    private Long id;

    @NotNull(message = "Animal name cannot be empty.")
    private String name;

    @NotNull(message = "Animal species cannot be empty.")
    private String species;

    @NotNull(message = "Animal breed cannot be empty.")
    private String breed;

    @NotNull(message = "Animal gender cannot be empty.")
    private String gender;

    @NotNull(message = "Animal color cannot be empty.")
    private String color;

    @NotNull(message = "Animal date of birth cannot be empty.")
    private LocalDate dateOfBirth;

    // @NotNull(message = "Customer ID cannot be empty.")
    // @Positive(message = "Customer ID must be positive.")
    private Long customerId;
}
