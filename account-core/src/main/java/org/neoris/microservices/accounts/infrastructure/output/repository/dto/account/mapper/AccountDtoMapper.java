package org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.accounts.domain.model.Account;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.AccountDTO;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.mapper.AccountClientDtoMapper;
import org.neoris.microservices.accounts.shared.EntityMapper;

@Mapper(uses = {AccountClientDtoMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    componentModel = ComponentModel.SPRING)
public interface AccountDtoMapper extends EntityMapper<AccountDTO, Account> {


}
