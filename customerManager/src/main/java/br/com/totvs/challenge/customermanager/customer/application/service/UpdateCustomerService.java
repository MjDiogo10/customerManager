package br.com.totvs.challenge.customermanager.customer.application.service;

import org.springframework.stereotype.Service;

import br.com.totvs.challenge.customermanager.customer.application.port.in.UpdateCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.in.rule.RulesUpdateCustomer;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCustomerService implements UpdateCustomerUseCase {

    private final RulesUpdateCustomer rulesUpdateCustomer;
    private final CustomerRepository customerRepository;

    @Override
    public Customer execute(Customer customer) {
        rulesUpdateCustomer.validate(customer);
        return customerRepository.update(customer);
    }
}
