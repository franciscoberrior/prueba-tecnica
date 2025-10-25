package org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.response.MovementResponseDTO;
import org.neoris.microservices.accounts.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface MovementResponseDtoMapper extends EntityMapper<MovementResponseDTO, Movement> {

}
