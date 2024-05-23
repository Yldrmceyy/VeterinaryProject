package dev.cey.repository;

import dev.cey.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    List<Doctor> findByIdAndAvailableDateDate(Long id, LocalDate localDate);
    List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone);
}
