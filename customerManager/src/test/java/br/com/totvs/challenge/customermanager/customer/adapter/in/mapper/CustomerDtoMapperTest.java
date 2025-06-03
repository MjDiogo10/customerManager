package br.com.totvs.challenge.customermanager.customer.adapter.in.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.totvs.challenge.customermanager.customer.adapter.in.dto.AddressDto;
import br.com.totvs.challenge.customermanager.customer.adapter.in.dto.CustomerDto;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerDtoMapperTest {

    @Mock
    private AddressDtoMapper addressDtoMapper;

    @InjectMocks
    private CustomerDtoMapper mapper;

    @Test
    void shouldMapCustomerDtoToDomainCorrectly() {
        AddressDto addressDto = new AddressDto(1L, "Rua A", "100", "Ap 1", "Centro",
                "São Paulo", "SP", "01000-000", "Brasil");

        CustomerDto dto = new CustomerDto(
                10L,
                "João",
                "12345678901",
                Set.of("11999999999", "11888888888"),
                Set.of(addressDto)
        );

        Address address = new Address();
        address.setId(1L);
        address.setStreet("Rua A");
        address.setNumber("100");
        address.setComplement("Ap 1");
        address.setNeighborhood("Centro");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setPostalCode("01000-000");
        address.setCountry("Brasil");

        when(addressDtoMapper.toDomainList(dto.addresses())).thenReturn(Set.of(address));

        Customer customer = mapper.toDomain(dto);

        assertNotNull(customer);
        assertEquals(dto.id(), customer.getId());
        assertEquals(dto.name(), customer.getName());
        assertEquals(dto.cpf(), customer.getCpf());
        assertEquals(dto.phones(), customer.getPhones());
        assertEquals(1, customer.getAddresses().size());
    }

    @Test
    void shouldMapCustomerToDtoCorrectly() {
        Address address = new Address();
        address.setId(2L);
        address.setStreet("Rua B");
        address.setNumber("200");
        address.setComplement("Ap 2");
        address.setNeighborhood("Bairro");
        address.setCity("Rio de Janeiro");
        address.setState("RJ");
        address.setPostalCode("20000-000");
        address.setCountry("Brasil");

        Customer customer = new Customer();
        customer.setId(20L);
        customer.setName("Maria");
        customer.setCpf("98765432100");
        customer.setPhones(Set.of("11911111111"));
        customer.setAddresses(Set.of(address));

        AddressDto addressDto = new AddressDto(2L, "Rua B", "200", "Ap 2", "Bairro",
                "Rio de Janeiro", "RJ", "20000-000", "Brasil");

        when(addressDtoMapper.toDtoList(customer.getAddresses())).thenReturn(Set.of(addressDto));

        CustomerDto dto = mapper.toDto(customer);

        assertNotNull(dto);
        assertEquals(customer.getId(), dto.id());
        assertEquals(customer.getName(), dto.name());
        assertEquals(customer.getCpf(), dto.cpf());
        assertEquals(customer.getPhones(), dto.phones());
        assertEquals(1, dto.addresses().size());
    }

    @Test
    void shouldReturnNullWhenDtoIsNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void shouldMapCustomerDtoListToDomainList() {
        AddressDto addressDto = new AddressDto(1L, "Rua 1", "10", null, "Bairro", "Cidade", "Estado", "00000-000", "Brasil");
        CustomerDto dto = new CustomerDto(1L, "João", "12345678900", Set.of("11999999999"), Set.of(addressDto));

        Address address = new Address();
        address.setId(1L);
        when(addressDtoMapper.toDomainList(Set.of(addressDto))).thenReturn(Set.of(address));

        Set<Customer> domainList = mapper.toDomainList(Set.of(dto));

        assertNotNull(domainList);
        assertEquals(1, domainList.size());
        Customer customer = domainList.iterator().next();
        assertEquals("João", customer.getName());
        assertEquals(Set.of("11999999999"), customer.getPhones());
        assertEquals(Set.of(address), customer.getAddresses());
    }

    @Test
    void shouldMapCustomerListToDtoList() {
        Address address = new Address();
        address.setId(1L);
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Maria");
        customer.setCpf("98765432100");
        customer.setPhones(Set.of("11988888888"));
        customer.setAddresses(Set.of(address));

        AddressDto addressDto = new AddressDto(1L, "Rua 2", "20", null, "Bairro B", "Cidade B", "Estado B", "11111-111", "Brasil");
        when(addressDtoMapper.toDtoList(Set.of(address))).thenReturn(Set.of(addressDto));

        Set<CustomerDto> dtoList = mapper.toDtoList(Set.of(customer));

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        CustomerDto dto = dtoList.iterator().next();
        assertEquals("Maria", dto.name());
        assertEquals(Set.of("11988888888"), dto.phones());
        assertEquals(Set.of(addressDto), dto.addresses());
    }

    @Test
    void shouldReturnEmptyDomainListWhenDtoListIsNull() {
        Set<Customer> domainList = mapper.toDomainList(null);
        assertNotNull(domainList);
        assertTrue(domainList.isEmpty());
    }

    @Test
    void shouldReturnEmptyDomainListWhenDtoListIsEmpty() {
        Set<Customer> domainList = mapper.toDomainList(Set.of());
        assertNotNull(domainList);
        assertTrue(domainList.isEmpty());
    }

    @Test
    void shouldReturnEmptyDtoListWhenDomainListIsNull() {
        Set<CustomerDto> dtoList = mapper.toDtoList(null);
        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }

    @Test
    void shouldReturnEmptyDtoListWhenDomainListIsEmpty() {
        Set<CustomerDto> dtoList = mapper.toDtoList(Set.of());
        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }
}
