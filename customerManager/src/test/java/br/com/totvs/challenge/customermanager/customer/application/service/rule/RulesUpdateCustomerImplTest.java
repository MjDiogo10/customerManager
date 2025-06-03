package br.com.totvs.challenge.customermanager.customer.application.service.rule;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.totvs.challenge.customermanager.customer.application.port.out.CustomerRepository;
import br.com.totvs.challenge.customermanager.customer.domain.exception.BusinessRuleException;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

@ExtendWith(MockitoExtension.class)
class RulesUpdateCustomerImplTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private RulesUpdateCustomerImpl rulesUpdateCustomer;

  private Customer validCustomer;

  @BeforeEach
  void setUp() {
    validCustomer = new Customer();
    validCustomer.setId(1L);
    validCustomer.setName("Valid Customer");
    validCustomer.setCpf("52998224725");
    validCustomer.setPhones(Set.of("5562991327958"));
  }

  @Test
  void shouldNotThrowExceptionForValidCustomer() {
    when(customerRepository.nameAlreadyRegistered(anyString(), anyLong())).thenReturn(false);
    when(customerRepository.cpfAlreadyRegistered(anyString(), anyLong())).thenReturn(false);
    when(customerRepository.phoneAlreadyRegistered(anyString(), anyLong())).thenReturn(false);

    assertDoesNotThrow(() -> rulesUpdateCustomer.validate(validCustomer));
  }

  @Test
  void shouldThrowExceptionForNullName() {
    validCustomer.setName(null);

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("Customer name must be at least 10 characters", ex.getMessage());
  }

  @Test
  void shouldThrowExceptionForShortName() {
    validCustomer.setName("Short");

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("Customer name must be at least 10 characters", ex.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenNameAlreadyRegistered() {
    when(customerRepository.nameAlreadyRegistered("Valid Customer", 1L)).thenReturn(true);

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("Customer name already registered", ex.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenCpfInvalid() {
    validCustomer.setCpf("123");

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("CPF number is invalid", ex.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenCpfAlreadyRegistered() {
    when(customerRepository.cpfAlreadyRegistered(validCustomer.getCpf(), 1L)).thenReturn(true);

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("CPF number already registered", ex.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenPhoneIsInvalid() {
    validCustomer.setPhones(Set.of("1234"));

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("Phone number is invalid", ex.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenPhoneAlreadyRegistered() {
    when(customerRepository.phoneAlreadyRegistered("5562991327958", 1L)).thenReturn(true);

    BusinessRuleException ex = assertThrows(BusinessRuleException.class,
      () -> rulesUpdateCustomer.validate(validCustomer));

    assertEquals("Phone number already registered", ex.getMessage());
  }
}
