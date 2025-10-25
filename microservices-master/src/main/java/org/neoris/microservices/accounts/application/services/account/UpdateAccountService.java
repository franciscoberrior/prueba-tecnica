package org.neoris.microservices.accounts.application.services.account;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.account.UpdateAccountServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.account.AccountUpdateService;
import org.neoris.microservices.accounts.domain.model.Account;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateAccountService implements UpdateAccountServiceInputPort {

  private final AccountUpdateService accountUpdateService;

  @Override
  public void update(Account account) {
    accountUpdateService.update(account);
  }

}
