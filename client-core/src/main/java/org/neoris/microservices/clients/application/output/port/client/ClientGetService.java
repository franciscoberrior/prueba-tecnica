package org.neoris.microservices.clients.application.output.port.client;

import org.neoris.microservices.clients.domain.model.Client;

public interface ClientGetService {
  Client getByDocumentId(String id);

}
