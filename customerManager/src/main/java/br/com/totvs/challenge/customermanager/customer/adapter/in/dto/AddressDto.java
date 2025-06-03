package br.com.totvs.challenge.customermanager.customer.adapter.in.dto;

public record AddressDto(Long id, String street, String number, String complement, String neighborhood,
                         String city, String state, String postalCode, String country) {
}
