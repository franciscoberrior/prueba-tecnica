package org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.request.MovementRequestDTO;

@Mapper(componentModel = ComponentModel.SPRING)
public interface MovementRequestDtoMapper {

  @Mapping(source = "accountNumber", target = "accountNumber.number")
  Movement toEntity(MovementRequestDTO dto);

  @Mapping(target = "accountNumber", source = "accountNumber.number")
  MovementRequestDTO toDto(Movement entity);

}
