package dev.cey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalSaveRequest {

        @NotNull(message = "Animal name cannot be empty.")
        private String name;

        @NotNull(message = "Animal species cannot be empty.")
        private String species;

        @NotNull(message = "Animal breed cannot be null.")
        private String breed;

        @NotNull(message = "Animal gender cannot be null.")
        private String gender;

        @NotNull(message = "Animal color cannot be null.")
        private String color;

        @NotNull(message = "Animal date of birth cannot be empty.")
        private LocalDate dateOfBirth;

        private Long customerId;

}