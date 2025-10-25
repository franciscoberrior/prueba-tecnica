package org.neoris.microservices.accounts.application.input.port.account;

import org.neoris.microservices.accounts.domain.model.Account;

public interface SaveAccountServiceInputPort {

  void save(Account account, String documentId);

}
