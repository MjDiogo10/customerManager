package br.com.totvs.challenge.customermanager.customer.domain.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Long id;
    private String name;
    private String cpf;
    private Set<String> phones;
    private Set<Address> addresses;
}
