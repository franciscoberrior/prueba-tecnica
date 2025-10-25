package org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Account;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.response.AccountResponseDTO;
import org.neoris.microservices.accounts.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AccountResponseDtoMapper extends EntityMapper<AccountResponseDTO, Account> {

}
