package dev.cey.business.abstracts;

import dev.cey.core.result.ResultData;
import dev.cey.dto.response.AppointmentResponse;
import dev.cey.dto.request.AppointmentSaveRequest;
import dev.cey.dto.request.AppointmentUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.entities.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IAppointmentService{
    ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest);
    Appointment get(Long id);
    ResultData<CursorResponse<AppointmentResponse>> cursor(int page, int pageSize);
    ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest);
    boolean delete(Long id);
    List<Appointment> findByDateTime(LocalDateTime localDateTime);
    ResultData<List<AppointmentResponse>> findByDoctorIdAndDateTimeBetween(Long id, LocalDate entryDate, LocalDate exitDate);
    ResultData<List<AppointmentResponse>> findByAnimalIdAndDateTimeBetween(Long id, LocalDate entryDate, LocalDate exitDate);
    Optional<Appointment> findByValueForValid(LocalDateTime dateTime, Long doctorId, Long animalId);
}

