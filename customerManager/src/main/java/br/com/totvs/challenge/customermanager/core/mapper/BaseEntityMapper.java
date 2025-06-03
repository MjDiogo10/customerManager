package br.com.totvs.challenge.customermanager.core.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface BaseEntityMapper<DOMAIN, ENTITY> {

    ENTITY toEntity(DOMAIN domain);
    DOMAIN toDomain(ENTITY entity);

    default Set<ENTITY> toEntityList(Set<DOMAIN> domainList) {
        if (domainList == null || domainList.isEmpty()) return new HashSet<>();
        return domainList.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    default Set<DOMAIN> toDomainList(Set<ENTITY> entityList) {
        if (entityList == null || entityList.isEmpty()) return new HashSet<>();
        return entityList.stream().map(this::toDomain).collect(Collectors.toSet());
    }
}
