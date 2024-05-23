package dev.cey.api;

import dev.cey.business.abstracts.IDoctorService;
import dev.cey.core.result.Result;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.DoctorSaveRequest;
import dev.cey.dto.request.DoctorUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.DoctorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final IDoctorService doctorService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        return this.doctorService.save(doctorSaveRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){
        return this.doctorService.update(doctorUpdateRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return this.doctorService.cursor(page, pageSize);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}
