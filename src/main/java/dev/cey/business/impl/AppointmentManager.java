package dev.cey.business.impl;

import dev.cey.business.abstracts.IAnimalService;
import dev.cey.business.abstracts.IAppointmentService;
import dev.cey.business.abstracts.IDoctorService;
import dev.cey.core.config.ConvertEntityToResponse;
import dev.cey.core.config.modelMapper.IModelMapperService;
import dev.cey.core.exception.DataAlreadyExistException;
import dev.cey.core.exception.NotFoundException;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.Msg;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.AppointmentSaveRequest;
import dev.cey.dto.request.AppointmentUpdateRequest;
import dev.cey.dto.response.AppointmentResponse;
import dev.cey.dto.response.CursorResponse;
import dev.cey.entities.Animal;
import dev.cey.entities.Appointment;
import dev.cey.entities.Doctor;
import dev.cey.repository.AppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Appointment, AppointmentResponse> convert;

    @Override
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        LocalDateTime dateTime = appointmentSaveRequest.getDateTime();
        if (dateTime.getMinute() != 0) {
            return ResultHelper.error("Lütfen dakika bilgisini '00' giriniz.");
        }

        Optional<Appointment> appointmentOptional = this.findByValueForValid(
                appointmentSaveRequest.getDateTime(),
                appointmentSaveRequest.getDoctorId(),
                appointmentSaveRequest.getAnimalId()
        );

        if (appointmentOptional.isPresent()) {
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Appointment.class));
        }

        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());

        List<Doctor> doctorList = this.doctorService.findByIdAndAvailableDateDate(appointmentSaveRequest.getDoctorId(), LocalDate.from(dateTime));
        List<Appointment> appointmentByDate = this.findByDateTime(dateTime);

        appointmentSaveRequest.setAnimalId(null);
        appointmentSaveRequest.setDateTime(null);
        appointmentSaveRequest.setDoctorId(null);

        Appointment saveAppointment = this.modelMapperService.forRequest().map(appointmentSaveRequest, Appointment.class);
        saveAppointment.setAnimal(animal);
        saveAppointment.setDoctor(doctor);
        saveAppointment.setDateTime(dateTime);

        if (doctorList.isEmpty()) {
            return ResultHelper.error("Doktor bu tarihte müsait değildir.");
        } else if (!appointmentByDate.isEmpty()) {
            return ResultHelper.error("Doktorun bu saatte randevusu bulunmaktadır.");
        } else {
            return ResultHelper.created(this.modelMapperService.forResponse().map(this.appointmentRepo.save(saveAppointment), AppointmentResponse.class));
        }
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<AppointmentResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage =  this.appointmentRepo.findAll(pageable);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }
    @Override
    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        this.get(appointmentUpdateRequest.getId());
        Appointment updateAppointment = this.modelMapperService.forRequest().map(appointmentUpdateRequest, Appointment.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.appointmentRepo.save(updateAppointment), AppointmentResponse.class));
    }
    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }
    @Override
    public List<Appointment> findByDateTime(LocalDateTime localDateTime) {
        return this.appointmentRepo.findByDateTime(localDateTime);
    }
    @Override
    public ResultData<List<AppointmentResponse>> findByDoctorIdAndDateTimeBetween(Long id, LocalDate entryDate, LocalDate exitDate) {
        LocalDateTime convertedEntryDate = entryDate.atStartOfDay();
        LocalDateTime convertedExitDate = exitDate.atStartOfDay().plusDays(1);
        List<Appointment> appointmentList = this.appointmentRepo.findByDoctorIdAndDateTimeBetween(id, convertedEntryDate, convertedExitDate);
        List<AppointmentResponse> appointmentResponseList = this.convert.convertToResponseList(appointmentList, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponseList);
    }
    @Override
    public ResultData<List<AppointmentResponse>> findByAnimalIdAndDateTimeBetween(Long id, LocalDate entryDate, LocalDate exitDate) {
        LocalDateTime convertedEntryDate = entryDate.atStartOfDay();
        LocalDateTime convertedExitDate = exitDate.atStartOfDay().plusDays(1);
        List<Appointment> appointmentList = this.appointmentRepo.findByAnimalIdAndDateTimeBetween(id, convertedEntryDate, convertedExitDate);
        List<AppointmentResponse> appointmentResponseList = this.convert.convertToResponseList(appointmentList, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponseList);
    }

    @Override
    public Optional<Appointment> findByValueForValid(LocalDateTime dateTime, Long doctorId, Long animalId) {
        return this.appointmentRepo.findByDateTimeAndDoctorIdAndAnimalId(dateTime, doctorId, animalId);
    }


}