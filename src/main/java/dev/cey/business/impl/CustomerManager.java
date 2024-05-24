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
    // Injected dependencies
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Customer, CustomerResponse> convert;

    @Override
    public ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest) {
        // Map the request DTO to the Customer entity
        Customer saveCustomer = this.modelMapperService.forRequest().map(customerSaveRequest, Customer.class);

        // Check if a customer with the same name, email, and phone already exists
        List<Customer> getByNamePhoneMail = this.findByNameAndMailAndPhone(
                saveCustomer.getName(),
                saveCustomer.getMail(),
                saveCustomer.getPhone());

        // Throw exception if such a customer exists
        if (!getByNamePhoneMail.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Customer.class));
        }

        // Save the new customer and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.customerRepo.save(saveCustomer), CustomerResponse.class));
    }

    @Override
    public Customer get(Long id) {
        // Retrieve the customer by id or throw NotFoundException if not found
        return this.customerRepo.findById((id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);

        // Retrieve all customers with pagination
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);

        // Convert customer entities to response DTOs with pagination
        Page<CustomerResponse> customerResponsePage = customerPage.map(customer -> this.modelMapperService.forResponse().map(customer, CustomerResponse.class));

        // Return the cursor response
        return ResultHelper.cursor(customerResponsePage);
    }

    @Override
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        // Check if the customer exists, throw NotFoundException if not
        this.get((customerUpdateRequest.getId()));

        // Map the update request DTO to the Customer entity
        Customer updateCustomer = this.modelMapperService.forRequest().map(customerUpdateRequest, Customer.class);

        // Save the updated customer and return the response
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.customerRepo.save(updateCustomer), CustomerResponse.class));
    }

    @Override
    public ResultData<List<CustomerResponse>> findByName(String name) {
        // Retrieve customers by name
        List<Customer> customerList = this.customerRepo.findByName(name);

        // Convert customer entities to response DTOs
        List<CustomerResponse> customerResponseList = this.convert.convertToResponseList(customerList, CustomerResponse.class);

        // Return the response
        return ResultHelper.success(customerResponseList);
    }

    @Override
    public List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone) {
        // Retrieve customers by name, email, and phone
        return this.customerRepo.findByNameAndMailAndPhone(name, mail, phone);
    }

    @Override
    public boolean delete(Long id) {
        // Retrieve the customer by id
        Customer customer = this.get(id);

        // Delete the customer and return success
        this.customerRepo.delete(customer);
        return true;
    }
}
