package dev.cey.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private int id;

    @Column(name = "vaccine_name")
    private String name;
    @Column(name = "vaccine_code")
    private String code;
    @Column(name = "vaccine_protectionStartDate")
    private LocalDate protectionStartDate;
    @Column(name = "vaccine_protectionFinishDate")
    private LocalDate protectionFinishDate;

    @ManyToOne()
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;
}