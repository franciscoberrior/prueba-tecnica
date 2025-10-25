package org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;
import org.neoris.microservices.accounts.domain.model.Client;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.ClientDTO;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AccountClientDtoMapper {

  AccountClientDtoMapper INSTANCE = Mappers.getMapper(AccountClientDtoMapper.class);

  @Mapping(target = "name", source = "clientId.name")
  @Mapping(target = "gender", source = "clientId.gender")
  @Mapping(target = "age", source = "clientId.age")
  @Mapping(target = "documentId", source = "clientId.documentId")
  @Mapping(target = "address", source = "clientId.address")
  @Mapping(target = "phone", source = "clientId.phone")
  Client toEntity(ClientDTO clientDTO);

  @Mapping(target = "clientId.name", source = "name")
  @Mapping(target = "clientId.gender", source = "gender")
  @Mapping(target = "clientId.age", source = "age")
  @Mapping(target = "clientId.documentId", source = "documentId")
  @Mapping(target = "clientId.address", source = "address")
  @Mapping(target = "clientId.phone", source = "phone")
  ClientDTO toDto(Client entity);

}
