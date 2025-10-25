package org.neoris.microservices.accounts.infrastructure.output.adapter.account;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.output.port.account.AccountDeleteService;
import org.neoris.microservices.accounts.application.output.port.account.AccountGetService;
import org.neoris.microservices.accounts.application.output.port.account.AccountSaveService;
import org.neoris.microservices.accounts.application.output.port.account.AccountUpdateService;
import org.neoris.microservices.accounts.domain.model.Account;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.AccountRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.mapper.AccountDtoMapper;
import org.neoris.microservices.accounts.shared.exception.infrastructure.adapter.AccountNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountAdapter implements AccountUpdateService, AccountSaveService,
    AccountDeleteService, AccountGetService {

  private final AccountRepository accountRepository;
  private final AccountDtoMapper accountDtoMapper;

  @Override
  public void deleteById(String id) {
    accountRepository.deleteById(id);
  }

  @Override
  public Account getById(String id) {
    return accountDtoMapper.toEntity(accountRepository.findById(id)
        .orElseThrow(AccountNotFoundException::new));
  }

  @Override
  public void save(Account account) {
    accountRepository.save(accountDtoMapper.toDto(account));
  }

  @Override
  public void update(Account account) {
    accountRepository.findById(account.getNumber())
        .ifPresent(acc -> accountRepository.save(accountDtoMapper.toDto(account)));
  }
}
