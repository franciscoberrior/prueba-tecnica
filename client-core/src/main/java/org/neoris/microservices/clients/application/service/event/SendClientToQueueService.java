package org.neoris.microservices.clients.application.service.event;

import java.util.List;
import lombok.AllArgsConstructor;
import org.neoris.microservices.clients.application.input.port.SendClientToQueueServiceInputPort;
import org.neoris.microservices.clients.application.output.port.event.ProcessPublisherService;
import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SendClientToQueueService implements SendClientToQueueServiceInputPort {

  private final List<ProcessPublisherService> processPublisherService;

  @Override
  public void send(Client client, ProcessClientTypeEnum type) {
    processPublisherService.stream().filter(pps -> pps.type().equals(type)).findFirst()
        .orElseThrow(RuntimeException::new).execute(client);
  }
}
