package dev.cey.repository;

import dev.cey.core.result.Result;
import dev.cey.core.result.ResultData;
import dev.cey.dto.request.AnimalSaveRequest;
import dev.cey.dto.response.AnimalResponse;
import dev.cey.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);

    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);

    ResultData<AnimalResponse> findById(Long id);

    ResultData<List<AnimalResponse>> findByName(String name);

    ResultData<List<AnimalResponse>> findAll ();

    Result delete(Long id);
    ResultData<List<AnimalResponse>> findByCustomerId(Long customerId);
}
