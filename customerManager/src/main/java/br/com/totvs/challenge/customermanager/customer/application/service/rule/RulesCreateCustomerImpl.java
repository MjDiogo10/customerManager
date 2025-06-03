package br.com.totvs.challenge.customermanager.customer.application.service.rule;

import org.springframework.stereotype.Component;

import br.com.totvs.challenge.customermanager.core.validator.CpfValidator;
import br.com.totvs.challenge.customermanager.core.validator.PhoneValidate;
import br.com.totvs.challenge.customermanager.customer.application.port.in.rule.RulesCreateCostumer;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.exception.BusinessRuleException;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RulesCreateCustomerImpl implements RulesCreateCostumer {

    private final CustomerRepository customerRepository;

    @Override
    public void validate(Customer customer) {

        if (customer.getName() == null || customer.getName().length() < 10) {
            throw new BusinessRuleException("Customer name must be at least 10 characters");
        }
        if (customerRepository.nameAlreadyRegistered(customer.getName())) {
            throw new BusinessRuleException("Customer name already registered");
        }

        if (!CpfValidator.isValid(customer.getCpf())) {
            throw new BusinessRuleException("CPF number is invalid");
        }
        if (customerRepository.cpfAlreadyRegistered(customer.getCpf())) {
            throw new BusinessRuleException("CPF number already registered");
        }

        for (String phone : customer.getPhones()) {
            if (!PhoneValidate.isValid(phone)) {
                throw new BusinessRuleException("Phone number is invalid");
            }
            else if (customerRepository.phoneAlreadyRegistered(phone)) {
                throw new BusinessRuleException("Phone number already registered");
            }
        }
    }
}
