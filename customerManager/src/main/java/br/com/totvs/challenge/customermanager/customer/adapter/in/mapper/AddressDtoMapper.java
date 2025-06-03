package br.com.totvs.challenge.customermanager.customer.adapter.in.mapper;

import org.springframework.stereotype.Component;

import br.com.totvs.challenge.customermanager.core.mapper.BaseDtoMapper;
import br.com.totvs.challenge.customermanager.customer.adapter.in.dto.AddressDto;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AddressDtoMapper implements BaseDtoMapper<AddressDto, Address> {

    @Override
    public Address toDomain(AddressDto addressDto) {
        if (addressDto == null) return null;

        Address address = new Address();
        address.setId(addressDto.id());
        address.setStreet(addressDto.street());
        address.setNumber(addressDto.number());
        address.setComplement(addressDto.complement());
        address.setNeighborhood(addressDto.neighborhood());
        address.setCity(addressDto.city());
        address.setState(addressDto.state());
        address.setPostalCode(addressDto.postalCode());
        address.setCountry(addressDto.country());

        return address;
    }

    @Override
    public AddressDto toDto(Address address) {
        if (address == null) return null;

        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry()
        );
    }
}
