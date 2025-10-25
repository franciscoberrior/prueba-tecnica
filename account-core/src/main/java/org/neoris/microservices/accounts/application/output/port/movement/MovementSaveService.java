package org.neoris.microservices.accounts.application.output.port.movement;

import org.neoris.microservices.accounts.domain.model.Movement;

public interface MovementSaveService {

  Movement save(Movement movement);

}
