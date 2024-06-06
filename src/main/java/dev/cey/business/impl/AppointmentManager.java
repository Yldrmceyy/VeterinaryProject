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
    // Injected dependencies
    private final AppointmentRepo appointmentRepo;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Appointment, AppointmentResponse> convert;

    @Override
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        // Get the appointment date and time
        LocalDateTime dateTime = appointmentSaveRequest.getDateTime();

        // Check if the minutes are set to '00'
        if (dateTime.getMinute() != 0) {
            return ResultHelper.error("Please enter the minute information as '00'.");
        }

        // Check if the appointment already exists
        Optional<Appointment> appointmentOptional = this.findByValueForValid(
                appointmentSaveRequest.getDateTime(),
                appointmentSaveRequest.getDoctorId(),
                appointmentSaveRequest.getAnimalId()
        );

        // Throw exception if such an appointment exists
        if (appointmentOptional.isPresent()) {
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Appointment.class));
        }

        // Retrieve the animal and doctor associated with the request
        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());

        // Check doctor's availability on the given date and existing appointments at the given time
        List<Doctor> doctorList = this.doctorService.findByIdAndAvailableDateDate(appointmentSaveRequest.getDoctorId(), LocalDate.from(dateTime));
        List<Appointment> appointmentByDate = this.findByDateTime(dateTime);

        // Clear redundant fields from the request
        appointmentSaveRequest.setAnimalId(null);
        appointmentSaveRequest.setDateTime(null);
        appointmentSaveRequest.setDoctorId(null);

        // Map the request DTO to the Appointment entity
        Appointment saveAppointment = this.modelMapperService.forRequest().map(appointmentSaveRequest, Appointment.class);
        saveAppointment.setAnimal(animal);
        saveAppointment.setDoctor(doctor);
        saveAppointment.setDateTime(dateTime);

        // Check if the doctor is available or has an existing appointment at the requested time
        if (doctorList.isEmpty()) {
            return ResultHelper.error("The doctor is not available on this date.");
        } else if (!appointmentByDate.isEmpty()) {
            return ResultHelper.error("The doctor has an appointment at this time.");
        } else {
            // Save the new appointment and return the response
            return ResultHelper.created(this.modelMapperService.forResponse().map(this.appointmentRepo.save(saveAppointment), AppointmentResponse.class));
        }
    }

    @Override
    public Appointment get(Long id) {
        // Retrieve the appointment by id or throw NotFoundException if not found
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AppointmentResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);

        // Retrieve all appointments with pagination
        Page<Appointment> appointmentPage = this.appointmentRepo.findAll(pageable);

        // Convert appointment entities to response DTOs with pagination
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapperService.forResponse().map(appointment, AppointmentResponse.class));

        // Return the cursor response
        return ResultHelper.cursor(appointmentResponsePage);
    }

    @Override
    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        // Check if the appointment exists, throw NotFoundException if not
        this.get(appointmentUpdateRequest.getId());

        // Map the update request DTO to the Appointment entity
        Appointment updateAppointment = this.modelMapperService.forRequest().map(appointmentUpdateRequest, Appointment.class);

        // Save the updated appointment and return the response
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.appointmentRepo.save(updateAppointment), AppointmentResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        // Retrieve the appointment by id
        Appointment appointment = this.get(id);

        // Delete the appointment and return success
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> findByDateTime(LocalDateTime localDateTime) {
        // Find appointments by date and time
        return this.appointmentRepo.findByDateTime(localDateTime);
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByDoctorIdAndDateTimeBetween(Long id, LocalDate entryDate, LocalDate exitDate) {
        // Convert entry and exit dates to LocalDateTime
        LocalDateTime convertedEntryDate = entryDate.atStartOfDay();
        LocalDateTime convertedExitDate = exitDate.atStartOfDay().plusDays(1);

        // Find appointments by doctor ID and date range
        List<Appointment> appointmentList = this.appointmentRepo.findByDoctorIdAndDateTimeBetween(id, convertedEntryDate, convertedExitDate);

        // Check if the appointment list is empty and return appropriate result
        if (appointmentList.isEmpty()) {
            return ResultHelper.NotFoundError(Msg.NOT_FOUND);
        }

        // Convert appointment entities to response DTOs
        List<AppointmentResponse> appointmentResponseList = this.convert.convertToResponseList(appointmentList, AppointmentResponse.class);

        // Return the result
        return ResultHelper.success(appointmentResponseList);
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByAnimalIdAndDateTimeBetween(Long id, LocalDate entryDate, LocalDate exitDate) {
        // Convert entry and exit dates to LocalDateTime
        LocalDateTime convertedEntryDate = entryDate.atStartOfDay();
        LocalDateTime convertedExitDate = exitDate.atStartOfDay().plusDays(1);

        // Find appointments by animal ID and date range
        List<Appointment> appointmentList = this.appointmentRepo.findByAnimalIdAndDateTimeBetween(id, convertedEntryDate, convertedExitDate);

        // Check if the appointment list is empty and return appropriate result
        if (appointmentList.isEmpty()) {
            return ResultHelper.NotFoundError(Msg.NOT_FOUND);
        }

        // Convert appointment entities to response DTOs
        List<AppointmentResponse> appointmentResponseList = this.convert.convertToResponseList(appointmentList, AppointmentResponse.class);

        // Return the result
        return ResultHelper.success(appointmentResponseList);
    }

    @Override
    public Optional<Appointment> findByValueForValid(LocalDateTime dateTime, Long doctorId, Long animalId) {
        // Find an appointment by date, doctor ID, and animal ID
        return this.appointmentRepo.findByDateTimeAndDoctorIdAndAnimalId(dateTime, doctorId, animalId);
    }
}
