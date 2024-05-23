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
    public final DoctorRepo doctorRepo;
    public final IModelMapperService modelMapperService;
    @Override
    public ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        List<Doctor> doctorList = this.findByNameAndMailAndPhone(
                doctorSaveRequest.getName(),
                doctorSaveRequest.getMail(),
                doctorSaveRequest.getPhone()
        );
        if (!doctorList.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Doctor.class));
        }
        Doctor saveDoctor = this.modelMapperService.forRequest().map(doctorSaveRequest, Doctor.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.doctorRepo.save(saveDoctor), DoctorResponse.class));
    }
    @Override
    public Doctor get(Long id) {
        return doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage =  this.doctorRepo.findAll(pageable);
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(doctor -> this.modelMapperService.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @Override
    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        this.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.modelMapperService.forRequest().map(doctorUpdateRequest, Doctor.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.doctorRepo.save(updateDoctor), DoctorResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }

    @Override
    public List<Doctor> findByIdAndAvailableDateDate(Long id, LocalDate localDate) {
        return this.doctorRepo.findByIdAndAvailableDateDate(id, localDate);
    }

    @Override
    public List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone) {
        return this.doctorRepo.findByNameAndMailAndPhone(name, mail, phone);
    }
}

