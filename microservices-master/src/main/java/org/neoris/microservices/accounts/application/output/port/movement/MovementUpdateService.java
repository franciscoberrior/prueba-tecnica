package org.neoris.microservices.accounts.application.output.port.movement;

import org.neoris.microservices.accounts.domain.model.Movement;

public interface MovementUpdateService {

  void update(Movement movement);

}
