package org.neoris.microservices.clients.application.service.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.clients.application.input.port.SaveClientServiceInputPort;
import org.neoris.microservices.clients.application.output.port.client.ClientSaveService;
import org.neoris.microservices.clients.application.output.port.client.anotation.CreateClientSend;
import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaveClientService implements SaveClientServiceInputPort {

  private final ClientSaveService clientSaveService;

  @CreateClientSend(type = ProcessClientTypeEnum.CREATE)
  @Override
  public Client save(Client client) {
    client.setStatus(true);
    return clientSaveService.save(client);
  }

}
