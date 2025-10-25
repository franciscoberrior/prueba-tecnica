package org.neoris.microservices.accounts.application.input.port.client;

import org.neoris.microservices.accounts.domain.model.Client;

public interface GetClientServiceInputPort {

  Client getById(String id);
}
