package dev.cey.repository;

import dev.cey.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepo  extends JpaRepository<AvailableDate, Long> {
}
