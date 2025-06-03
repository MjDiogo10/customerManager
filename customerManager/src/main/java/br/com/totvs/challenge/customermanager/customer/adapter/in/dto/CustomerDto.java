package br.com.totvs.challenge.customermanager.customer.adapter.in.dto;

import java.util.Set;

public record CustomerDto(Long id, String name, String cpf, Set<String> phones, Set<AddressDto> addresses) {
}
