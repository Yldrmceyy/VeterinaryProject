package dev.cey.business.impl;

import dev.cey.business.abstracts.IAvailableDateService;
import dev.cey.core.config.modelMapper.IModelMapperService;
import dev.cey.core.exception.DataAlreadyExistException;
import dev.cey.core.exception.NotFoundException;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.Msg;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.AvailableDateSaveRequest;
import dev.cey.dto.request.AvailableDateUpdateRequest;
import dev.cey.dto.response.AvailableDateResponse;
import dev.cey.dto.response.CursorResponse;
import dev.cey.entities.AvailableDate;
import dev.cey.repository.AvailableDateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {
    // Injected dependencies
    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {
        // Check if the available date already exists for the given doctor
        List<AvailableDate> availableDateList = this.availableDateRepo.findByDateAndDoctor(
                availableDateSaveRequest.getDate(),
                availableDateSaveRequest.getDoctor());

        // Throw exception if such an available date exists
        if (!availableDateList.isEmpty()) {
            throw new DataAlreadyExistException(Msg.getEntityForMsg(AvailableDate.class));
        }

        // Map the request DTO to the AvailableDate entity
        AvailableDate saveAvailableDate = this.modelMapperService.forRequest().map(availableDateSaveRequest, AvailableDate.class);

        // Save the new available date and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.availableDateRepo.save(saveAvailableDate), AvailableDateResponse.class));
    }

    @Override
    public AvailableDate get(Long id) {
        // Retrieve the available date by id or throw NotFoundException if not found
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);

        // Retrieve all available dates with pagination
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);

        // Convert available date entities to response DTOs with pagination
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate -> this.modelMapperService.forResponse().map(availableDate, AvailableDateResponse.class));

        // Return the cursor response
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @Override
    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        // Check if the available date exists, throw NotFoundException if not
        this.get(availableDateUpdateRequest.getId());

        // Map the update request DTO to the AvailableDate entity
        AvailableDate updateAvailableDate = this.modelMapperService.forResponse().map(availableDateUpdateRequest, AvailableDate.class);

        // Save the updated available date and return the response
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.availableDateRepo.save(updateAvailableDate), AvailableDateResponse.class));
    }

    @Override
    public boolean delete(Long id) {
        // Retrieve the available date by id
        AvailableDate availableDate = this.get(id);

        // Delete the available date and return success
        this.availableDateRepo.delete(availableDate);
        return true;
    }
}
