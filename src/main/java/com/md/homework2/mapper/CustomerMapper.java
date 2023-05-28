package com.md.homework2.mapper;

import com.md.homework2.dto.CustomerDto;
import com.md.homework2.dto.requests.CreateCustomerRequest;
import com.md.homework2.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer convertToCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDto convertToCustomerDTO(Customer customer);

    List<CustomerDto> convertToCustomerDTOList (List<Customer> customers);

}
