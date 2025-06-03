package br.com.totvs.challenge.customermanager.customer.application.service;

import org.springframework.stereotype.Service;

import br.com.totvs.challenge.customermanager.customer.application.port.in.DeleteCustomerUseCase;
import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteCustomerService implements DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public void execute(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
