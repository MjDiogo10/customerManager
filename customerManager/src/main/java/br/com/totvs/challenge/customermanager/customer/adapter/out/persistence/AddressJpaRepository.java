package br.com.totvs.challenge.customermanager.customer.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.totvs.challenge.customermanager.customer.adapter.out.entity.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

  @Modifying
  @Query("DELETE FROM AddressEntity a WHERE a.customer.id = :customerId")
  void deleteByCustomerId(Long customerId);

  @Modifying
  @Query("DELETE FROM AddressEntity a WHERE a.customer.id = :customerId AND a.id NOT IN :ids")
  void deleteByCustomerIdAndIdNotIn(Long customerId, List<Long> ids);
}
