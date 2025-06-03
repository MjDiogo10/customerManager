package br.com.totvs.challenge.customermanager.customer.adapter.in.mapper;

import org.springframework.stereotype.Component;

import br.com.totvs.challenge.customermanager.core.mapper.BaseDtoMapper;
import br.com.totvs.challenge.customermanager.customer.adapter.in.dto.CustomerDto;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerDtoMapper implements BaseDtoMapper<CustomerDto, Customer> {

    private final AddressDtoMapper addressDtoMapper;

    @Override
    public Customer toDomain(CustomerDto customerDto) {
        if (customerDto == null) return null;

        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setName(customerDto.name());
        customer.setCpf(customerDto.cpf());
        customer.setPhones(customerDto.phones());
        customer.setAddresses(addressDtoMapper.toDomainList(customerDto.addresses()));

        return customer;
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getPhones(),
                addressDtoMapper.toDtoList(customer.getAddresses())
        );
    }
}
