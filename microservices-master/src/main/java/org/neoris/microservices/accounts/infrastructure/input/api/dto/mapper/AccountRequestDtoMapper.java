package org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Account;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.request.AccountRequestDTO;
import org.neoris.microservices.accounts.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AccountRequestDtoMapper extends EntityMapper<AccountRequestDTO, Account> {

}
