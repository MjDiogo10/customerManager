package br.com.totvs.challenge.customermanager.customer.adapter.out.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.totvs.challenge.customermanager.core.mapper.BaseEntityMapper;
import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.AddressEntity;
import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.CustomerEntity;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerEntityMapper implements BaseEntityMapper<Customer, CustomerEntity> {

    private final AddressEntityMapper addressEntityMapper;

    @Override
    public CustomerEntity toEntity(Customer customer) {
        if (customer == null) return null;

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setCpf(customer.getCpf());
        customerEntity.setPhones(customer.getPhones());

        Set<AddressEntity> addressEntities = addressEntityMapper.toEntityList(customer.getAddresses());

        addressEntities = addressEntities.stream()
                .peek(addressEntity -> addressEntity.setCustomer(customerEntity))
                .collect(Collectors.toSet());

        customerEntity.setAddresses(addressEntities);

        return customerEntity;
    }

    @Override
    public Customer toDomain(CustomerEntity customerEntity) {
        if (customerEntity == null) return null;

        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setCpf(customerEntity.getCpf());
        customer.setPhones(customerEntity.getPhones());
        customer.setAddresses(addressEntityMapper.toDomainList(customerEntity.getAddresses()));

        return customer;
    }
}
