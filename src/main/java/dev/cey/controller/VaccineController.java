package dev.cey.controller;

import dev.cey.business.abstracts.IVaccineService;
import dev.cey.core.result.Result;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.VaccineSaveRequest;
import dev.cey.dto.request.VaccineUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.VaccineResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final IVaccineService vaccineService;




    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){
        return this.vaccineService.save(vaccineSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return this.vaccineService.cursor(page, pageSize);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        return this.vaccineService.update(vaccineUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccineByAnimalId(@PathVariable("id") Long animalId){
        return this.vaccineService.findByAnimalId(animalId);
    }

    @GetMapping("/findByDate")
    public ResultData<List<VaccineResponse>> getVaccinesByDate(
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate exitDate
    ){
        return this.vaccineService.findByDate(entryDate,exitDate);
    }
}
