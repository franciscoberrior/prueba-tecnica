package org.neoris.microservices.clients.application.service.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.clients.application.input.port.GetClientServiceInputPort;
import org.neoris.microservices.clients.application.output.port.client.ClientGetService;
import org.neoris.microservices.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetClientService implements GetClientServiceInputPort {

  private final ClientGetService clientGetService;

  @Override
  public Client getById(String id) {
    return clientGetService.getByDocumentId(id);
  }
}
