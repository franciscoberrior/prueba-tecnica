package org.neoris.microservices.clients.infrastructure.input.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.clients.domain.model.Client;
import org.neoris.microservices.clients.infrastructure.input.api.dto.response.ClientResponseDTO;
import org.neoris.microservices.clients.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ClientResponseDtoMapper extends EntityMapper<ClientResponseDTO, Client> {

}
