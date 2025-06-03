package br.com.totvs.challenge.customermanager.customer.adapter.in.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.totvs.challenge.customermanager.customer.adapter.in.dto.AddressDto;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;

@ExtendWith(MockitoExtension.class)
public class AddressDtoMapperTest {

    @InjectMocks
    private AddressDtoMapper mapper;

    @Test
    void shouldMapAddressDtoToDomainCorrectly() {
        AddressDto dto = new AddressDto(
                1L, "Rua A", "123", "Ap 1", "Centro",
                "São Paulo", "SP", "01000-000", "Brasil"
        );

        Address address = mapper.toDomain(dto);

        assertNotNull(address);
        assertEquals(dto.id(), address.getId());
        assertEquals(dto.street(), address.getStreet());
        assertEquals(dto.number(), address.getNumber());
        assertEquals(dto.complement(), address.getComplement());
        assertEquals(dto.neighborhood(), address.getNeighborhood());
        assertEquals(dto.city(), address.getCity());
        assertEquals(dto.state(), address.getState());
        assertEquals(dto.postalCode(), address.getPostalCode());
        assertEquals(dto.country(), address.getCountry());
    }

    @Test
    void shouldMapAddressToDtoCorrectly() {
        Address address = new Address();
        address.setId(2L);
        address.setStreet("Rua B");
        address.setNumber("456");
        address.setComplement("Casa");
        address.setNeighborhood("Bairro");
        address.setCity("Rio de Janeiro");
        address.setState("RJ");
        address.setPostalCode("20000-000");
        address.setCountry("Brasil");

        AddressDto dto = mapper.toDto(address);

        assertNotNull(dto);
        assertEquals(address.getId(), dto.id());
        assertEquals(address.getStreet(), dto.street());
        assertEquals(address.getNumber(), dto.number());
        assertEquals(address.getComplement(), dto.complement());
        assertEquals(address.getNeighborhood(), dto.neighborhood());
        assertEquals(address.getCity(), dto.city());
        assertEquals(address.getState(), dto.state());
        assertEquals(address.getPostalCode(), dto.postalCode());
        assertEquals(address.getCountry(), dto.country());
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
    void shouldMapListOfDtosToDomains() {
        AddressDto dto = new AddressDto(1L, "Rua A", "100", null, "Centro",
                "São Paulo", "SP", "01000-000", "Brasil");

        Set<Address> result = mapper.toDomainList(Set.of(dto));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Rua A", result.iterator().next().getStreet());
    }

    @Test
    void shouldMapListOfDomainsToDtos() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("Rua A");
        address.setNumber("100");
        address.setNeighborhood("Centro");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setPostalCode("01000-000");
        address.setCountry("Brasil");

        Set<AddressDto> result = mapper.toDtoList(Set.of(address));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Rua A", result.iterator().next().street());
    }

    @Test
    void shouldReturnEmptyDomainListWhenDtoListIsNull() {
        Set<Address> domainList = mapper.toDomainList(null);
        assertNotNull(domainList);
        assertTrue(domainList.isEmpty());
    }

    @Test
    void shouldReturnEmptyDomainListWhenDtoListIsEmpty() {
        Set<Address> domainList = mapper.toDomainList(Set.of());
        assertNotNull(domainList);
        assertTrue(domainList.isEmpty());
    }

    @Test
    void shouldReturnEmptyDtoListWhenDomainListIsNull() {
        Set<AddressDto> dtoList = mapper.toDtoList(null);
        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }

    @Test
    void shouldReturnEmptyDtoListWhenDomainListIsEmpty() {
        Set<AddressDto> dtoList = mapper.toDtoList(Set.of());
        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }
}
