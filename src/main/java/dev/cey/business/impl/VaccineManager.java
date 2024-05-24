package dev.cey.business.impl;

import dev.cey.business.abstracts.IAnimalService;
import dev.cey.business.abstracts.IVaccineService;
import dev.cey.core.config.ConvertEntityToResponse;
import dev.cey.core.config.modelMapper.IModelMapperService;
import dev.cey.core.exception.DataAlreadyExistException;
import dev.cey.core.exception.NotFoundException;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.Msg;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.VaccineSaveRequest;
import dev.cey.dto.request.VaccineUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.VaccineResponse;
import dev.cey.entities.Animal;
import dev.cey.entities.Vaccine;
import dev.cey.repository.VaccineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {
    // Dependencies for the Vaccine repository, model mapper service, and animal service
    private final VaccineRepo vaccineRepo;
    private final IModelMapperService modelMapperService;
    private final IAnimalService animalService;
    private final ConvertEntityToResponse<Vaccine, VaccineResponse> convert;

    @Override
    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {
        // Check for existing vaccines with the same code and name
        List<Vaccine> existVaccines = this.findByCodeAndName(vaccineSaveRequest.getCode(), vaccineSaveRequest.getName());
        if (!existVaccines.isEmpty() && existVaccines.get(0).getProtectionFinishDate().isAfter(LocalDate.now())) {
            return ResultHelper.error("The protection period of the vaccine with the same code is still ongoing!");
        }
        if (!existVaccines.isEmpty()) {
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Vaccine.class));
        }
        // Get the animal by ID
        Animal animal = this.animalService.get(vaccineSaveRequest.getAnimalId());
        vaccineSaveRequest.setAnimalId(null);

        // Map the request DTO to the Vaccine entity
        Vaccine saveVaccine = this.modelMapperService.forRequest().map(vaccineSaveRequest, Vaccine.class);
        saveVaccine.setAnimal(animal);

        // Save the new vaccine and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.vaccineRepo.save(saveVaccine), VaccineResponse.class));
    }

    @Override
    public Vaccine get(Long id) {
        // Get the vaccine by id, throw NotFoundException if not found
        return this.vaccineRepo.findById((id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize) {
        // Create a pageable request
        Pageable pageable = PageRequest.of(page, pageSize);
        // Get all vaccines with pagination
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pageable);
        // Map Vaccine entities to response DTOs and return with pagination
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long id) {
        // Find vaccines by animal ID
        List<Vaccine> vaccineList = this.vaccineRepo.findByAnimalId(id);
        // Convert the list of Vaccine entities to response DTOs
        List<VaccineResponse> vaccineResponseList = this.convert.convertToResponseList(vaccineList, VaccineResponse.class);
        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByDate(LocalDate entryDate, LocalDate exitDate) {
        // Find vaccines between the given dates
        List<Vaccine> vaccineList = this.vaccineRepo.findByprotectionFinishDateBetween(entryDate, exitDate);
        if (vaccineList.isEmpty()) {
            return ResultHelper.error("No vaccines found within the specified date range.");
        }
        // Convert the list of Vaccine entities to response DTOs
        List<VaccineResponse> vaccineResponseList = this.convert.convertToResponseList(vaccineList, VaccineResponse.class);
        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public List<Vaccine> findByCodeAndName(String code, String name) {
        // Find vaccines by code and name
        return this.vaccineRepo.findByCodeAndName(code, name);
    }

    @Override
    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        // Get the existing vaccine by ID
        Vaccine existingVaccine = this.get(vaccineUpdateRequest.getId());
        // Map the update request DTO to the existing Vaccine entity
        this.modelMapperService.forRequest().map(vaccineUpdateRequest, existingVaccine);
        // Save the updated vaccine and return the response
        Vaccine savedVaccine = this.vaccineRepo.save(existingVaccine);
        return ResultHelper.success(this.modelMapperService.forResponse().map(savedVaccine, VaccineResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        // Get the vaccine by id
        Vaccine vaccine = this.get(id);
        // Delete the vaccine and return true
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    public boolean isTrue() {
        // Dummy method to return true
        return true;
    }
}
