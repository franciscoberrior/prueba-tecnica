package org.neoris.microservices.accounts.application.services.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.client.UpdateClientServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.client.ClientUpdateService;
import org.neoris.microservices.accounts.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateClientService implements UpdateClientServiceInputPort {

  private final ClientUpdateService clientUpdateService;

  @Override
  public void update(Client client) {
    clientUpdateService.update(client);
  }

}
