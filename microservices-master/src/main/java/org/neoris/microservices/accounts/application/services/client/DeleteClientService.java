package org.neoris.microservices.accounts.application.services.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.client.DeleteClientServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.client.ClientDeleteService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteClientService implements DeleteClientServiceInputPort {

  private final ClientDeleteService clientDeleteService;

  @Override
  public void delete(Long id) {
    clientDeleteService.delete(id);
  }

}
