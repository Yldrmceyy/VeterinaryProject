package dev.cey.business.abstracts;

import dev.cey.core.result.ResultData;
import dev.cey.dto.request.VaccineSaveRequest;
import dev.cey.dto.request.VaccineUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.VaccineResponse;
import dev.cey.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);
    Vaccine get(Long id);
    ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize);
    ResultData<List<VaccineResponse>> findByAnimalId(Long id);
    ResultData<List<VaccineResponse>> findByDate(LocalDate entryDate, LocalDate exitDate);
    List<Vaccine> findByCodeAndName(String code, String name);
    ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);
    boolean delete(Long id);
}
