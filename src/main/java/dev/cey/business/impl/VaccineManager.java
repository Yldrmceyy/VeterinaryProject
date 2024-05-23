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
    private final VaccineRepo vaccineRepo;
    private final IModelMapperService modelMapperService;
    private final IAnimalService animalService;
    private final ConvertEntityToResponse<Vaccine, VaccineResponse> convert;
    @Override
    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {

        List<Vaccine> existVaccines = this.findByCodeAndName(vaccineSaveRequest.getCode(), vaccineSaveRequest.getName());
        if (!existVaccines.isEmpty() && existVaccines.get(0).getProtectionFinishDate().isAfter(LocalDate.now())){
            return ResultHelper.error("The protection period of the vaccine with the same code is still ongoing!");
        }
        if (!existVaccines.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Vaccine.class));
        }
        Animal animal = this.animalService.get(vaccineSaveRequest.getAnimalId());
        vaccineSaveRequest.setAnimalId(null);

        Vaccine saveVaccine = this.modelMapperService.forRequest().map(vaccineSaveRequest, Vaccine.class);
        saveVaccine.setAnimal(animal);

        return ResultHelper.created(this.modelMapperService.forResponse().map(this.vaccineRepo.save(saveVaccine), VaccineResponse.class));
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById((id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pageable);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long id) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByAnimalId(id);
        List<VaccineResponse> vaccineResponseList = this.convert.convertToResponseList(vaccineList, VaccineResponse.class);
        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByDate(LocalDate entryDate, LocalDate exitDate) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByprotectionFinishDateBetween(entryDate, exitDate);
        List<VaccineResponse> vaccineResponseList = this.convert.convertToResponseList(vaccineList, VaccineResponse.class);
        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public List<Vaccine> findByCodeAndName(String code, String name) {
        return this.vaccineRepo.findByCodeAndName(code,name);
    }

    @Override
    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        this.get(vaccineUpdateRequest.getId());
        Vaccine updateVaccine = this.modelMapperService.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateVaccine, VaccineResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    public boolean isTrue(){
        return true;
    }

}
