package br.com.totvs.challenge.customermanager.customer.adapter.out.mapper;

import org.springframework.stereotype.Component;

import br.com.totvs.challenge.customermanager.core.mapper.BaseEntityMapper;
import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.AddressEntity;
import br.com.totvs.challenge.customermanager.customer.domain.model.Address;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AddressEntityMapper implements BaseEntityMapper<Address, AddressEntity> {

    @Override
    public AddressEntity toEntity(Address address) {
        if (address == null) return null;

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(address.getId());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setNumber(address.getNumber());
        addressEntity.setComplement(address.getComplement());
        addressEntity.setNeighborhood(address.getNeighborhood());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setCountry(address.getCountry());

        return addressEntity;
    }

    @Override
    public Address toDomain(AddressEntity addressEntity) {
        if (addressEntity == null) return null;

        Address address = new Address();
        address.setId(addressEntity.getId());
        address.setStreet(addressEntity.getStreet());
        address.setNumber(addressEntity.getNumber());
        address.setComplement(addressEntity.getComplement());
        address.setNeighborhood(addressEntity.getNeighborhood());
        address.setCity(addressEntity.getCity());
        address.setState(addressEntity.getState());
        address.setPostalCode(addressEntity.getPostalCode());
        address.setCountry(addressEntity.getCountry());

        return address;
    }
}
