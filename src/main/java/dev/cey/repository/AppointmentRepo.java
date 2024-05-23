package dev.cey.repository;

import dev.cey.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDateTime(LocalDateTime localDateTime);
    List<Appointment> findByDoctorIdAndDateTimeBetween(long id, LocalDateTime entryDate, LocalDateTime exitDate);
    List<Appointment> findByAnimalIdAndDateTimeBetween(long id, LocalDateTime entryDate, LocalDateTime exitDate);
    Optional<Appointment> findByDateTimeAndDoctorIdAndAnimalId(LocalDateTime dateTime, Long doctorId, Long animalId);
}
