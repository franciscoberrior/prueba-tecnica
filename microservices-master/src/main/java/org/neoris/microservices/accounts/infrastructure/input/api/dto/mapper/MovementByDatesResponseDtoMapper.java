package org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.response.MovementByDatesDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface MovementByDatesResponseDtoMapper{

  @Mapping(target = "date", source = "dateMovement")
  @Mapping(target = "clientName", source = "accountNumber.clientId.name")
  @Mapping(target = "accountNumber", source = "accountNumber.number")
  @Mapping(target = "type", source = "accountNumber.type")
  @Mapping(target = "initialBalance", source = "accountNumber.initialBalance")
  @Mapping(target = "status", source = "accountNumber.status")
  @Mapping(target = "movement", source = "value")
  @Mapping(target = "availableBalance", source = "balance")
  MovementByDatesDto toDto(Movement entity);

  List<MovementByDatesDto> toDto(List<Movement> entities);
}
