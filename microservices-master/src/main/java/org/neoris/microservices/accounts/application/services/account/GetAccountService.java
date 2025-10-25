package org.neoris.microservices.accounts.application.services.account;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.account.GetAccountServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.account.AccountGetService;
import org.neoris.microservices.accounts.domain.model.Account;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetAccountService implements GetAccountServiceInputPort {

  private final AccountGetService accountGetService;

  @Override
  public Account getById(String id) {
    return accountGetService.getById(id);
  }
}
