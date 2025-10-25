package org.neoris.microservices.accounts.application.services.account;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.account.DeleteAccountServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.account.AccountDeleteService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteAccountService implements DeleteAccountServiceInputPort {

  private final AccountDeleteService accountDeleteService;

  @Override
  public void delete(String id) {
    accountDeleteService.deleteById(id);
  }

}
