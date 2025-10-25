package org.neoris.microservices.accounts.application.output.port.account;

import org.neoris.microservices.accounts.domain.model.Account;

public interface AccountUpdateService {

  void update(Account account);

}
