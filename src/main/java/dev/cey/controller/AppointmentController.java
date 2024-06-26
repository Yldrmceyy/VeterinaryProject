package dev.cey.controller;

import dev.cey.business.abstracts.IAppointmentService;
import dev.cey.core.result.Result;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.AppointmentSaveRequest;
import dev.cey.dto.request.AppointmentUpdateRequest;
import dev.cey.dto.response.AppointmentResponse;
import dev.cey.dto.response.CursorResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.save(appointmentSaveRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return this.appointmentService.cursor(page, pageSize);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return this.appointmentService.update(appointmentUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping("/filterByDoctorDate/{doctorId}-{findByDate}")
    public ResultData<List<AppointmentResponse>> getDoctorIdAndDate(
            @PathVariable("doctorId") Long id,
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate
    ) {
        return this.appointmentService.findByDoctorIdAndDateTimeBetween(id, entryDate, exitDate);
    }

    @GetMapping("/filterByAnimalDate/{animalId}-{findByDate}")
    public ResultData<List<AppointmentResponse>> getAnimalIdAndDate(
            @PathVariable("animalId") Long id,
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate
    ) {
        return this.appointmentService.findByAnimalIdAndDateTimeBetween(id, entryDate, exitDate);
    }
}
