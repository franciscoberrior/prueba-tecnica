package org.neoris.microservices.clients.application.input.port;

import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;

public interface SendClientToQueueServiceInputPort {

  void send(Client client, ProcessClientTypeEnum type);

}
