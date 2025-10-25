package org.neoris.microservices.accounts.application.services.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.client.GetClientServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.client.ClientGetService;
import org.neoris.microservices.accounts.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetClientService implements GetClientServiceInputPort {

  private final ClientGetService clientGetService;

  @Override
  public Client getById(String id) {
    return clientGetService.get(id);
  }
}
