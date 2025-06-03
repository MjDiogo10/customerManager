package br.com.totvs.challenge.customermanager.customer.application.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.totvs.challenge.customermanager.customer.application.port.in.ListCustomersUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListCustomersService implements ListCustomersUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Set<Customer> execute() {
        return customerRepository.findAll();
    }
}
