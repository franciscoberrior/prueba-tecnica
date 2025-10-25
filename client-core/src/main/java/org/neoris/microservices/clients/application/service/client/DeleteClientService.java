package org.neoris.microservices.clients.application.service.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.clients.application.input.port.DeleteClientServiceInputPort;
import org.neoris.microservices.clients.application.output.port.client.ClientDeleteService;
import org.neoris.microservices.clients.application.output.port.client.ClientGetService;
import org.neoris.microservices.clients.application.output.port.client.anotation.CreateClientSend;
import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteClientService implements DeleteClientServiceInputPort {

  private final ClientDeleteService clientDeleteService;
  private final ClientGetService clientGetService;

  @CreateClientSend(type = ProcessClientTypeEnum.DELETE)
  @Override
  public Client delete(String id) {
    Client client = clientGetService.getByDocumentId(id);
    clientDeleteService.delete(client.getId());
    return client;
  }

}
