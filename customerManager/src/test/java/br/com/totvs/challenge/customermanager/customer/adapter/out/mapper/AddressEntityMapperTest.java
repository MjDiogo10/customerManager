package br.com.totvs.challenge.customermanager.customer.adapter.out.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.AddressEntity;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;

@ExtendWith(MockitoExtension.class)
public class AddressEntityMapperTest {

    @InjectMocks
    private AddressEntityMapper mapper;

    @Test
    void shouldMapToDomainCorrectly() {
        AddressEntity entity = new AddressEntity();
        entity.setId(1L);
        entity.setStreet("Rua A");
        entity.setNumber("123");
        entity.setComplement("Apto 4");
        entity.setNeighborhood("Centro");
        entity.setCity("São Paulo");
        entity.setState("SP");
        entity.setPostalCode("12345-678");
        entity.setCountry("Brasil");

        Address domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getStreet(), domain.getStreet());
        assertEquals(entity.getNumber(), domain.getNumber());
        assertEquals(entity.getComplement(), domain.getComplement());
        assertEquals(entity.getNeighborhood(), domain.getNeighborhood());
        assertEquals(entity.getCity(), domain.getCity());
        assertEquals(entity.getState(), domain.getState());
        assertEquals(entity.getPostalCode(), domain.getPostalCode());
        assertEquals(entity.getCountry(), domain.getCountry());
    }

    @Test
    void shouldMapToEntityCorrectly() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("Rua B");
        address.setNumber("456");
        address.setComplement("Casa");
        address.setNeighborhood("Bairro");
        address.setCity("Rio de Janeiro");
        address.setState("RJ");
        address.setPostalCode("98765-432");
        address.setCountry("Brasil");

        AddressEntity entity = mapper.toEntity(address);

        assertNotNull(entity);
        assertEquals(address.getId(), entity.getId());
        assertEquals(address.getStreet(), entity.getStreet());
        assertEquals(address.getNumber(), entity.getNumber());
        assertEquals(address.getComplement(), entity.getComplement());
        assertEquals(address.getNeighborhood(), entity.getNeighborhood());
        assertEquals(address.getCity(), entity.getCity());
        assertEquals(address.getState(), entity.getState());
        assertEquals(address.getPostalCode(), entity.getPostalCode());
        assertEquals(address.getCountry(), entity.getCountry());
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
        Set<Address> result = mapper.toDomainList(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenToDomainListWithEmptySet() {
        Set<Address> result = mapper.toDomainList(Set.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenToEntityListWithNull() {
        Set<AddressEntity> result = mapper.toEntityList(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenToEntityListWithEmptySet() {
        Set<AddressEntity> result = mapper.toEntityList(Set.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldMapToEntityListCorrectly() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("Rua A");

        Set<AddressEntity> entities = mapper.toEntityList(Set.of(address));

        assertNotNull(entities);
        assertEquals(1, entities.size());

        AddressEntity entity = entities.iterator().next();
        assertEquals(address.getId(), entity.getId());
        assertEquals(address.getStreet(), entity.getStreet());
    }

    @Test
    void shouldMapToDomainListCorrectly() {
        AddressEntity entity = new AddressEntity();
        entity.setId(2L);
        entity.setStreet("Rua B");

        Set<Address> addresses = mapper.toDomainList(Set.of(entity));

        assertNotNull(addresses);
        assertEquals(1, addresses.size());

        Address address = addresses.iterator().next();
        assertEquals(entity.getId(), address.getId());
        assertEquals(entity.getStreet(), address.getStreet());
    }
}

