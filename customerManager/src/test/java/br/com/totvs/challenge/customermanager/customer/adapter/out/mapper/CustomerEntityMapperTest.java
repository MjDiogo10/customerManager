package br.com.totvs.challenge.customermanager.customer.adapter.out.mapper;

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

import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.AddressEntity;
import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.CustomerEntity;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;
import br.com.totvs.challenge.customermanager.customer.domain.model.Customer;

@ExtendWith(MockitoExtension.class)
class CustomerEntityMapperTest {

    @Mock
    private AddressEntityMapper addressEntityMapper;

    @InjectMocks
    private CustomerEntityMapper mapper;

    @Test
    void shouldMapToDomainCorrectly() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        entity.setName("Test User");
        entity.setCpf("12345678901");
        entity.setPhones(Set.of("11999999999"));

        AddressEntity addressEntity = new AddressEntity();
        entity.setAddresses(Set.of(addressEntity));

        Address address = new Address();
        when(addressEntityMapper.toDomainList(Set.of(addressEntity))).thenReturn(Set.of(address));

        Customer domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getName(), domain.getName());
        assertEquals(entity.getCpf(), domain.getCpf());
        assertEquals(entity.getPhones(), domain.getPhones());
        assertEquals(1, domain.getAddresses().size());
    }

    @Test
    void shouldMapToEntityCorrectly() {
        Customer domain = new Customer();
        domain.setId(1L);
        domain.setName("Test User");
        domain.setCpf("12345678901");
        domain.setPhones(Set.of("11999999999"));

        Address address = new Address();
        domain.setAddresses(Set.of(address));

        AddressEntity addressEntity = new AddressEntity();
        Set<AddressEntity> addressEntities = Set.of(addressEntity);

        when(addressEntityMapper.toEntityList(Set.of(address))).thenReturn(addressEntities);

        CustomerEntity entity = mapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getName(), entity.getName());
        assertEquals(domain.getCpf(), entity.getCpf());
        assertEquals(domain.getPhones(), entity.getPhones());
        assertEquals(1, entity.getAddresses().size());
        assertTrue(entity.getAddresses().stream().allMatch(a -> a.getCustomer() == entity));
    }

    @Test
    void shouldReturnNullWhenToDomainWithNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenToEntityWithNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void shouldReturnEmptyListWhenToDomainListWithNull() {
        Set<Customer> result = mapper.toDomainList(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenToDomainListWithEmptySet() {
        Set<Customer> result = mapper.toDomainList(Set.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenToEntityListWithNull() {
        Set<CustomerEntity> result = mapper.toEntityList(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenToEntityListWithEmptySet() {
        Set<CustomerEntity> result = mapper.toEntityList(Set.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldMapToEntityListCorrectly() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("João");
        customer.setCpf("12345678900");
        customer.setPhones(Set.of("1234-5678"));

        Address address = new Address();
        address.setId(10L);
        address.setStreet("Rua A");
        customer.setAddresses(Set.of(address));

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(10L);
        addressEntity.setStreet("Rua A");

        when(addressEntityMapper.toEntityList(Set.of(address))).thenReturn(Set.of(addressEntity));

        Set<CustomerEntity> entities = mapper.toEntityList(Set.of(customer));

        assertNotNull(entities);
        assertEquals(1, entities.size());

        CustomerEntity entity = entities.iterator().next();
        assertEquals(customer.getId(), entity.getId());
        assertEquals(customer.getName(), entity.getName());
        assertEquals(customer.getCpf(), entity.getCpf());
        assertEquals(customer.getPhones(), entity.getPhones());
        assertNotNull(entity.getAddresses());
        assertEquals(1, entity.getAddresses().size());
    }


    @Test
    void shouldMapToDomainListCorrectly() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        entity.setName("Maria");
        entity.setCpf("98765432100");
        entity.setPhones(Set.of("9876-5432"));

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(20L);
        addressEntity.setStreet("Rua B");
        entity.setAddresses(Set.of(addressEntity));

        Address address = new Address();
        address.setId(20L);
        address.setStreet("Rua B");

        when(addressEntityMapper.toDomainList(Set.of(addressEntity))).thenReturn(Set.of(address));

        Set<Customer> domains = mapper.toDomainList(Set.of(entity));

        assertNotNull(domains);
        assertEquals(1, domains.size());

        Customer customer = domains.iterator().next();
        assertEquals(entity.getId(), customer.getId());
        assertEquals(entity.getName(), customer.getName());
        assertEquals(entity.getCpf(), customer.getCpf());
        assertEquals(entity.getPhones(), customer.getPhones());
        assertNotNull(customer.getAddresses());
        assertEquals(1, customer.getAddresses().size());
    }
}

