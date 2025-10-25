package org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Client;
import org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.ClientRequestDTO;
import org.neoris.microservices.accounts.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AccountClientRequestDtoMapper extends EntityMapper<ClientRequestDTO, Client> {

}
