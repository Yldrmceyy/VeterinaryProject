package dev.cey.business.abstracts;


import dev.cey.core.result.ResultData;
import dev.cey.dto.request.CustomerSaveRequest;
import dev.cey.dto.request.CustomerUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.CustomerResponse;
import dev.cey.entities.Customer;

import java.util.List;

public interface ICustomerService {
    ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest);
    Customer get(Long id);
    ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize);
    ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);
    ResultData<List<CustomerResponse>> findByName(String name);
    List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone);
    boolean delete(Long id);
}
