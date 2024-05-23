package dev.cey.business.abstracts;

import dev.cey.entities.Animal;
import dev.cey.core.result.ResultData;
import dev.cey.dto.request.AnimalUpdateRequest;
import dev.cey.dto.request.AnimalSaveRequest;
import dev.cey.dto.response.AnimalResponse;
import dev.cey.dto.response.CursorResponse;

import java.util.List;


public interface IAnimalService {
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);
    Animal get(Long id);
    ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize);
    ResultData<List<AnimalResponse>> findByName(String name);
    ResultData<List<AnimalResponse>> findByCustomerId(Long id);
    List<Animal> findByNameAndSpeciesAndBreedAndGender(String name,String species,String breed,String gender);
    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);
    boolean delete(Long id);
}
