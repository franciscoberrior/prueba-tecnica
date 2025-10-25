package org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.mapper.AccountDtoMapper;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement.MovementDTO;
import org.neoris.microservices.accounts.shared.EntityMapper;

@Mapper(uses = {AccountDtoMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    componentModel = ComponentModel.SPRING)
public interface MovementDtoMapper extends EntityMapper<MovementDTO, Movement> {

}
