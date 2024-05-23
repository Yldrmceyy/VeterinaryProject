package dev.cey.business.abstracts;

import dev.cey.core.result.ResultData;
import dev.cey.dto.request.DoctorSaveRequest;
import dev.cey.dto.request.DoctorUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.DoctorResponse;
import dev.cey.entities.Doctor;

import java.time.LocalDate;
import java.util.List;

public interface IDoctorService {
    ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest);
    Doctor get(Long id);
    ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize);
    ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);
    boolean delete(Long id);
    List<Doctor> findByIdAndAvailableDateDate(Long id, LocalDate localDate);
    List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone);
}
