package br.com.totvs.challenge.customermanager.customer.application.port.out;

import java.util.Set;

import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

public interface CustomerRepository {

    Customer insert(Customer customer);

    Customer update(Customer customer);

    void deleteById(Long customerId);

    Set<Customer> findAll();

    Customer findById(Long customerId);

    boolean nameAlreadyRegistered(String customerName);

    boolean nameAlreadyRegistered(String customerName, Long customerId);

    boolean cpfAlreadyRegistered(String customerCpf);

    boolean cpfAlreadyRegistered(String customerCpf, Long customerId);

    boolean phoneAlreadyRegistered(String customerPhone);

    boolean phoneAlreadyRegistered(String customerPhone, Long customerId);
}
