package br.com.totvs.challenge.customermanager.customer.application.port.in;

import java.util.Set;

import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

public interface ListCustomersUseCase {

    Set<Customer> execute();
}
