package org.neoris.microservices.accounts.application.services.account;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.account.SaveAccountServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.account.AccountSaveService;
import org.neoris.microservices.accounts.application.services.client.GetClientService;
import org.neoris.microservices.accounts.domain.model.Account;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaveAccountService implements SaveAccountServiceInputPort {

  private final AccountSaveService accountSaveService;
  private final GetClientService getClientService;

  @Override
  public void save(Account account, String documentId) {
    account.setStatus(true);
    account.setClientId(getClientService.getById(documentId));
    accountSaveService.save(account);
  }

}
