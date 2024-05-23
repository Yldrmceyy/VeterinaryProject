package dev.cey.business.impl;

import dev.cey.business.abstracts.ICustomerService;
import dev.cey.core.config.ConvertEntityToResponse;
import dev.cey.core.config.modelMapper.IModelMapperService;
import dev.cey.core.exception.DataAlreadyExistException;
import dev.cey.core.exception.NotFoundException;
import dev.cey.core.result.ResultData;
import dev.cey.core.utilies.Msg;
import dev.cey.core.utilies.ResultHelper;
import dev.cey.dto.request.CustomerSaveRequest;
import dev.cey.dto.request.CustomerUpdateRequest;
import dev.cey.dto.response.CursorResponse;
import dev.cey.dto.response.CustomerResponse;
import dev.cey.entities.Customer;
import dev.cey.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Customer, CustomerResponse> convert;

    @Override
    public ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapperService.forRequest().map(customerSaveRequest, Customer.class);
        List<Customer> getByNamePhoneMail = this.findByNameAndMailAndPhone(
                saveCustomer.getName(),
                saveCustomer.getMail(),
                saveCustomer.getPhone());
        if (!getByNamePhoneMail.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Customer.class));
        }
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.customerRepo.save(saveCustomer), CustomerResponse.class));
    }

    @Override
    public Customer get(Long id) {
        return this.customerRepo.findById((id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);
        Page<CustomerResponse> customerResponsePage = customerPage.map(customer -> this.modelMapperService.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }

    @Override
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        this.get((customerUpdateRequest.getId()));
        Customer updateCustomer = this.modelMapperService.forRequest().map(customerUpdateRequest, Customer.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.customerRepo.save(updateCustomer), CustomerResponse.class));
    }

    @Override
    public ResultData<List<CustomerResponse>> findByName(String name) {
        List<Customer> customerList = this.customerRepo.findByName(name);
        List<CustomerResponse> customerResponseList = this.convert.convertToResponseList(customerList, CustomerResponse.class);
        return ResultHelper.success(customerResponseList);
    }

    @Override
    public List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone) {
        return this.customerRepo.findByNameAndMailAndPhone(name, mail, phone);
    }


    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }
}
