package org.neoris.microservices.clients.application.output.port.event;

import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;

public interface ProcessPublisherService{

  ProcessClientTypeEnum type();
  void execute(Client client);
}
