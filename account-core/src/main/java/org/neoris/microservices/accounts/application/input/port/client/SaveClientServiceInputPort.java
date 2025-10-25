package org.neoris.microservices.accounts.application.input.port.client;

import org.neoris.microservices.accounts.domain.model.Client;

public interface SaveClientServiceInputPort {

  void save(Client client);

}
