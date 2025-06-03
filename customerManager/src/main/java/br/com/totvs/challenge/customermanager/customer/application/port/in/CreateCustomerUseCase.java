package br.com.totvs.challenge.customermanager.customer.application.port.in;

import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

public interface CreateCustomerUseCase {

    Customer execute(Customer customer);
}
