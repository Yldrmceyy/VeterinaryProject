package dev.cey.repository;

import dev.cey.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimalId(Long id);
    List<Vaccine> findByprotectionFinishDateBetween(LocalDate entryDate, LocalDate exitDate);
    List<Vaccine> findByCodeAndName(String code, String name);
}
