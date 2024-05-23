package dev.cey.business.abstracts;

import dev.cey.core.result.ResultData;
import dev.cey.dto.request.AvailableDateSaveRequest;
import dev.cey.dto.request.AvailableDateUpdateRequest;
import dev.cey.dto.response.AvailableDateResponse;
import dev.cey.dto.response.CursorResponse;
import dev.cey.entities.AvailableDate;

public interface IAvailableDateService {
    ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDate get(Long id);
    ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize);
    ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);
    boolean delete(Long id);
}