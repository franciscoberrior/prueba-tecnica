package org.neoris.microservices.accounts.application.output.port.client;

import org.neoris.microservices.accounts.domain.model.Client;

public interface ClientUpdateService {

  void update(Client client);

}
