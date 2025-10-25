package org.neoris.microservices.clients.application.input.port;

import org.neoris.microservices.clients.domain.model.Client;

public interface GetClientServiceInputPort {

  Client getById(String id);
}
