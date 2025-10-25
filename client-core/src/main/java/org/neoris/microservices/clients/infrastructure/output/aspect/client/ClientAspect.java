package org.neoris.microservices.clients.infrastructure.output.aspect.client;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.neoris.microservices.clients.application.input.port.SendClientToQueueServiceInputPort;
import org.neoris.microservices.clients.application.output.port.client.anotation.CreateClientSend;
import org.neoris.microservices.clients.domain.model.Client;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ClientAspect {

  private final SendClientToQueueServiceInputPort sendClientToQueueService;

  @AfterReturning(value = "@annotation(createClientSend)", returning = "client")
  public void sendToQueue(CreateClientSend createClientSend,
      Client client) {
    sendClientToQueueService.send(client, createClientSend.type());
  }
}
