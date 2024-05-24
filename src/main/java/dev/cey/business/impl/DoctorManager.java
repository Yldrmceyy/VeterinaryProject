package dev.cey.business.impl;

import dev.cey.business.abstracts.IDoctorService;
import dev.cey.core.config.modelMapper.IModelMapperService;
import dev.cey.core.exception.DataAlreadyExistException;
import dev.cey.core.exception.NotFoundException;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.Msg;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.DoctorSaveRequest;
import dev.cey.dto.request.DoctorUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.DoctorResponse;
import dev.cey.entities.Doctor;
import dev.cey.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {
    // Dependencies for the doctor repository and model mapper service
    public final DoctorRepo doctorRepo;
    public final IModelMapperService modelMapperService;

    @Override
    public ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        // Check for doctors with the same name, email, and phone number
        List<Doctor> doctorList = this.findByNameAndMailAndPhone(
                doctorSaveRequest.getName(),
                doctorSaveRequest.getMail(),
                doctorSaveRequest.getPhone()
        );
        if (!doctorList.isEmpty()) {
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Doctor.class));
        }
        // Map the request DTO to the Doctor entity
        Doctor saveDoctor = this.modelMapperService.forRequest().map(doctorSaveRequest, Doctor.class);
        // Save the new doctor and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.doctorRepo.save(saveDoctor), DoctorResponse.class));
    }

    @Override
    public Doctor get(Long id) {
        // Get the doctor by id, throw NotFoundException if not found
        return doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize) {
        // Create a pageable request
        Pageable pageable = PageRequest.of(page, pageSize);
        // Get all doctors with pagination
        Page<Doctor> doctorPage = this.doctorRepo.findAll(pageable);
        // Map Doctor entities to response DTOs and return with pagination
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(doctor -> this.modelMapperService.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @Override
    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        // Check if the doctor exists, throw NotFoundException if not found
        this.get(doctorUpdateRequest.getId());
        // Map the update request DTO to the Doctor entity
        Doctor updateDoctor = this.modelMapperService.forRequest().map(doctorUpdateRequest, Doctor.class);
        // Save the updated doctor and return the response
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.doctorRepo.save(updateDoctor), DoctorResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        // Get the doctor by id
        Doctor doctor = this.get(id);
        // Delete the doctor and return true
        this.doctorRepo.delete(doctor);
        return true;
    }

    @Override
    public List<Doctor> findByIdAndAvailableDateDate(Long id, LocalDate localDate) {
        // Get doctors by id and available date
        return this.doctorRepo.findByIdAndAvailableDateDate(id, localDate);
    }

    @Override
    public List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone) {
        // Get doctors by name, email, and phone number
        return this.doctorRepo.findByNameAndMailAndPhone(name, mail, phone);
    }
}
