package br.com.totvs.challenge.customermanager.core.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface BaseDtoMapper<DTO, DOMAIN> {

    DOMAIN toDomain(DTO dto);
    DTO toDto(DOMAIN domain);

    default Set<DOMAIN> toDomainList(Set<DTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) return new HashSet<>();
        return dtoList.stream().map(this::toDomain).collect(Collectors.toSet());
    }

    default Set<DTO> toDtoList(Set<DOMAIN> domainList) {
        if (domainList == null || domainList.isEmpty()) return new HashSet<>();
        return domainList.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
