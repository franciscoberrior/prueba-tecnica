package org.neoris.microservices.clients.application.service.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.clients.application.input.port.UpdateClientServiceInputPort;
import org.neoris.microservices.clients.application.output.port.client.ClientUpdateService;
import org.neoris.microservices.clients.application.output.port.client.anotation.CreateClientSend;
import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateClientService implements UpdateClientServiceInputPort {

  private final ClientUpdateService clientUpdateService;

  @CreateClientSend(type = ProcessClientTypeEnum.UPDATE)
  @Override
  public Client update(Client client){
    return clientUpdateService.update(client);
  }

}
