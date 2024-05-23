package dev.cey.repository;

import dev.cey.entities.AvailableDate;
import dev.cey.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findByDateAndDoctor(LocalDate availableDate, Doctor doctor);
}
