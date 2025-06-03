package br.com.totvs.challenge.customermanager.customer.adapter.out.persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.CustomerEntity;
import br.com.totvs.challenge.customermanager.customer.adapter.out.mapper.CustomerEntityMapper;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerEntityMapper customerEntityMapper;
    private final CustomerJpaRepository customerJpaRepository;
    private final AddressJpaRepository addressJpaRepository;

    @Override
    public Customer insert(Customer customer) {
        CustomerEntity customerEntity = customerEntityMapper.toEntity(customer);
        customerEntity = customerJpaRepository.save(customerEntity);
        return customerEntityMapper.toDomain(customerEntity);
    }

    @Transactional
    @Override
    public Customer update(Customer customer) {
        List<Long> idsAddresses = customer.getAddresses().stream().map(Address::getId).toList();

        if (idsAddresses.isEmpty()) addressJpaRepository.deleteByCustomerId(customer.getId());
        else addressJpaRepository.deleteByCustomerIdAndIdNotIn(customer.getId(), idsAddresses);

        CustomerEntity customerEntity = customerEntityMapper.toEntity(customer);
        customerEntity = customerJpaRepository.save(customerEntity);
        return customerEntityMapper.toDomain(customerEntity);
    }

    @Override
    public void deleteById(Long customerId) {
        customerJpaRepository.deleteById(customerId);
    }

    @Override
    public Set<Customer> findAll() {
        Set<CustomerEntity> customerEntities = new HashSet<>(customerJpaRepository.findAll());
        return customerEntityMapper.toDomainList(customerEntities);
    }

    @Override
    public Customer findById(Long customerId) {
        CustomerEntity customerEntity = customerJpaRepository.findById(customerId).orElse(null);
        return customerEntityMapper.toDomain(customerEntity);
    }

    @Override
    public boolean nameAlreadyRegistered(String customerName) {
        return customerJpaRepository.existsByName(customerName);
    }

    @Override
    public boolean nameAlreadyRegistered(String customerName, Long customerId) {
        return customerJpaRepository.existsByNameAndIdNot(customerName, customerId);
    }

    @Override
    public boolean cpfAlreadyRegistered(String customerCpf) {
        return customerJpaRepository.existsByCpf(customerCpf);
    }

    @Override
    public boolean cpfAlreadyRegistered(String customerCpf, Long customerId) {
        return customerJpaRepository.existsByCpfAndIdNot(customerCpf, customerId);
    }

    @Override
    public boolean phoneAlreadyRegistered(String customerPhone) {
        return customerJpaRepository.existsByPhone(customerPhone);
    }

    @Override
    public boolean phoneAlreadyRegistered(String customerPhone, Long customerId) {
        return customerJpaRepository.existsByPhone(customerPhone, customerId);
    }
}
