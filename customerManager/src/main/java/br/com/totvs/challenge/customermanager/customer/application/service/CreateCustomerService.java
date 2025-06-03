package br.com.totvs.challenge.customermanager.customer.application.service;

import org.springframework.stereotype.Service;

import br.com.totvs.challenge.customermanager.customer.application.port.in.CreateCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.in.rule.RulesCreateCostumer;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateCustomerService implements CreateCustomerUseCase {

    private final RulesCreateCostumer rulesCreateCostumer;
    private final CustomerRepository customerRepository;

    @Override
    public Customer execute(Customer customer) {
        rulesCreateCostumer.validate(customer);
        return customerRepository.insert(customer);
    }
}
