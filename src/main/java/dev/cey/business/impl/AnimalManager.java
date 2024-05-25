package dev.cey.business.impl;

import dev.cey.business.abstracts.IAnimalService;
import dev.cey.business.abstracts.ICustomerService;
import dev.cey.core.config.ConvertEntityToResponse;
import dev.cey.core.config.modelMapper.IModelMapperService;
import dev.cey.core.exception.DataAlreadyExistException;
import dev.cey.core.exception.NotFoundException;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.Msg;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.AnimalSaveRequest;
import dev.cey.dto.request.AnimalUpdateRequest;
import dev.cey.dto.response.AnimalResponse;
import dev.cey.dto.response.CursorResponse;
import dev.cey.entities.Animal;
import dev.cey.entities.Customer;
import dev.cey.repository.AnimalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {
    // Injected dependencies
    private final ConvertEntityToResponse<Animal, AnimalResponse> convert;
    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapperService;
    private final ICustomerService customerService;

    @Override
    public ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        // Retrieve the customer associated with the given customerId
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        animalSaveRequest.setCustomerId(null);

        // Map the request DTO to the Animal entity
        Animal saveAnimal = this.modelMapperService.forRequest().map(animalSaveRequest, Animal.class);
        saveAnimal.setCustomer(customer);

        // Check if an animal with the same name, species, breed, and gender already exists
        List<Animal> animalList = this.findByNameAndSpeciesAndBreedAndGender(
                animalSaveRequest.getName(),
                animalSaveRequest.getSpecies(),
                animalSaveRequest.getBreed(),
                animalSaveRequest.getGender()
        );

        // Throw exception if such an animal exists
        if (!animalList.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Animal.class));
        }

        // Save the new animal and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.animalRepo.save(saveAnimal), AnimalResponse.class));
    }

    @Override
    public Animal get(Long id) {
        // Retrieve the animal by id or throw NotFoundException if not found
        return this.animalRepo.findById((id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);

        // Retrieve all animals with pagination
        Page<Animal> animalPage = this.animalRepo.findAll(pageable);

        // Convert animal entities to response DTOs with pagination
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> this.modelMapperService.forResponse().map(animal, AnimalResponse.class));

        // Return the cursor response
        return ResultHelper.cursor(animalResponsePage);
    }

    @Override
    public ResultData<List<AnimalResponse>> findByName(String name) {
        // Find animals by name
        List<Animal> animalList = this.animalRepo.findByName(name);

        // Check if the customer list is empty and return appropriate result
        if (animalList.isEmpty()) {
            return ResultHelper.NotFoundError(Msg.NOT_FOUND);
        }

        // Convert to response DTOs
        List<AnimalResponse> animalResponseList = this.convert.convertToResponseList(animalList, AnimalResponse.class);

        // Return the result
        return ResultHelper.success(animalResponseList);
    }

    @Override
    public ResultData<List<AnimalResponse>> findByCustomerId(Long id) {
        // Find animals by customerId
        List<Animal> animalList = this.animalRepo.findByCustomerId(id);

        // Check if the customer list is empty and return appropriate result
        if (animalList.isEmpty()) {
            return ResultHelper.NotFoundError(Msg.NOT_FOUND);
        }

        // Convert to response DTOs
        List<AnimalResponse> animalResponseList = this.convert.convertToResponseList(animalList, AnimalResponse.class);

        // Return the result
        return ResultHelper.success(animalResponseList);
    }

    @Override
    public List<Animal> findByNameAndSpeciesAndBreedAndGender(String name, String species, String breed, String gender) {
        // Find animals by name, species, breed, and gender
        return this.animalRepo.findByNameAndSpeciesAndBreedAndGender(name, species, breed, gender);
    }

    @Override
    public ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        // Check if the animal exists, throw NotFoundException if not
        this.get(animalUpdateRequest.getId());

        // Map the update request DTO to the Animal entity
        Animal updateAnimal = this.modelMapperService.forRequest().map(animalUpdateRequest, Animal.class);

        // Save the updated animal and return the response
        this.animalRepo.save(updateAnimal);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        // Retrieve the animal by id
        Animal animal = this.get(id);

        // Delete the animal and return success
        this.animalRepo.delete(animal);
        return true;
    }
}
