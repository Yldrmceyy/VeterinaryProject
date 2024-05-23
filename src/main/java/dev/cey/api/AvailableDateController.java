package dev.cey.api;

import dev.cey.business.abstracts.IAvailableDateService;
import dev.cey.core.result.ResultData;
import dev.cey.core.result.Result;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.response.AvailableDateResponse;
import dev.cey.dto.request.AvailableDateSaveRequest;
import dev.cey.dto.request.AvailableDateUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        return this.availableDateService.save(availableDateSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        return this.availableDateService.cursor(page, pageSize);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
        return this.availableDateService.update(availableDateUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}
