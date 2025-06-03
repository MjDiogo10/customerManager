package br.com.totvs.challenge.customermanager.customer.application.port.in.rule;

import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

public interface RulesCreateCostumer {

    void validate(Customer customer);
}
