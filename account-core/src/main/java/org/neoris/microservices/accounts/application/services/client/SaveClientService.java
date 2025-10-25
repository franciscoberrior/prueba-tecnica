package org.neoris.microservices.accounts.application.services.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.client.SaveClientServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.client.ClientSaveService;
import org.neoris.microservices.accounts.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaveClientService implements SaveClientServiceInputPort {

  private final ClientSaveService clientSaveService;

  @Override
  public void save(Client client) {
    clientSaveService.save(client);
  }

}
