package br.com.totvs.challenge.customermanager.customer.application.service;

import org.springframework.stereotype.Service;

import br.com.totvs.challenge.customermanager.customer.application.port.in.GetCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetCustomerService implements GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Customer execute(Long customerId) {
        return customerRepository.findById(customerId);
    }
}
