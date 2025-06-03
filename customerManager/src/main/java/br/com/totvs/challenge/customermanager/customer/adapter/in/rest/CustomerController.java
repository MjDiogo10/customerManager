package br.com.totvs.challenge.customermanager.customer.adapter.in.rest;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.totvs.challenge.customermanager.customer.adapter.in.dto.CustomerDto;
import br.com.totvs.challenge.customermanager.customer.adapter.in.mapper.CustomerDtoMapper;
import br.com.totvs.challenge.customermanager.customer.application.port.in.CreateCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.in.DeleteCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.in.GetCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.in.ListCustomersUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.in.UpdateCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerDtoMapper customerDtoMapper;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final ListCustomersUseCase listCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        Customer InsertedCustomer = createCustomerUseCase.execute(customerDtoMapper.toDomain(customerDto));
        return customerDtoMapper.toDto(InsertedCustomer);
    }

    @PutMapping
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        Customer UpdatedCustomer = updateCustomerUseCase.execute(customerDtoMapper.toDomain(customerDto));
        return customerDtoMapper.toDto(UpdatedCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        deleteCustomerUseCase.execute(id);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public Set<CustomerDto> listAllCustomers() {
        Set<Customer> customers = listCustomersUseCase.execute();
        return customerDtoMapper.toDtoList(customers);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Long id) {
        Customer customer = getCustomerUseCase.execute(id);
        return customerDtoMapper.toDto(customer);
    }
}
