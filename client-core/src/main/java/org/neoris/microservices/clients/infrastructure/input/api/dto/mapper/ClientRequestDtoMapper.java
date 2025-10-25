package org.neoris.microservices.clients.infrastructure.input.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.clients.domain.model.Client;
import org.neoris.microservices.clients.infrastructure.input.api.dto.request.ClientRequestDTO;
import org.neoris.microservices.clients.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ClientRequestDtoMapper extends EntityMapper<ClientRequestDTO, Client> {

}
