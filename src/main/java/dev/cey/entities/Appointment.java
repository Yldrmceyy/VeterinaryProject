package dev.cey.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime appointmentDateTime;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @JsonIgnore
    private Animal animal;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @JsonIgnore
    private Doctor doctor;
}