package br.com.totvs.challenge.customermanager.customer.adapter.out.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Table(name = "TB_CUSTOMER")
@Entity
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @ElementCollection
    @CollectionTable(
            name = "TB_CUSTOMER_PHONE",
            joinColumns = @JoinColumn(name = "customer_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"phone"})
    )
    @Column(name = "phone", length = 13)
    private Set<String> phones;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses;
}
